package com.mantulife.common.utils;

import com.mantulife.common.model.dto.CheckParentIdDto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class GetTreeUtils {

    private GetTreeUtils() {

    }

    /**
     *
     * 判断 将要更新的上级部门 是否为自己的 下级部门
     * @param level1Menus 数据
     * @param dto dto
     * @return  return return
     */
    public static boolean checkParentId(List<CheckParentIdDto> level1Menus, CheckParentIdDto dto){
        AtomicBoolean f = new AtomicBoolean(false);
        // id  parentId
        List<CheckParentIdDto> collect = level1Menus.stream().filter(companyDepartmentEntity ->
                !companyDepartmentEntity.getChildren().isEmpty() && getChildrens(companyDepartmentEntity, dto.getId())
        ).collect(Collectors.toList());
        List<CheckParentIdDto> tree = treeToList(collect);
        Map<Long, CheckParentIdDto> apiMap = tree.stream().collect(Collectors.toMap(CheckParentIdDto::getId,x->x));

        collect.forEach(companyDepartmentEntity -> {
            if (!f.get()) {
                boolean childrens = getChildrens(companyDepartmentEntity, dto.getParentId());
                //判断 id 和 parentId 的层级
                if (childrens) {
                    boolean b = checkIsOk(apiMap, dto);
                    f.set(!b);
                }else {
                    f.set(false);
                }
            }
        });
        return f.get();
    }

    /**
     * 返回树形结构
     * @param entities entities
     * @return  return 返回
     */
    public static List<CheckParentIdDto> listWithTree(List<CheckParentIdDto> entities) {
        //2.组装父子的树形结构
        //2.1 找到所有一级分类
        return entities.stream().filter(departmentEntity ->
                departmentEntity.getParentId() == 0L
        ).map(menu->{
            menu.setChildren(getChildrens(menu, entities));
            return menu;
        }).collect(Collectors.toList());
    }

    private static boolean getChildrens(CheckParentIdDto root, Long parentId){
        if (root.getId().compareTo(parentId) == 0) {
            return true;
        }
        boolean present = root.getChildren().stream().anyMatch(checkParentIdDto ->
                checkParentIdDto.getId().compareTo(parentId) == 0
        );
        if (!present) {
            present = root.getChildren().stream().anyMatch(checkParentIdDto ->
                    getChildrens(checkParentIdDto, parentId)
            );
        }
        return present;

    }

    /**
     * 递归查找所有子级
     * @param dto dto
     * @param dto dto
     * @return  return return
     */
    private static List<CheckParentIdDto> getChildrens(CheckParentIdDto dto, List<CheckParentIdDto> all) {
        return all.stream().filter(categoryEntity ->
                categoryEntity.getParentId().compareTo(dto.getId()) == 0
        ).map(categoryEntity -> {
            //1 找到子级
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).collect(Collectors.toList());
    }


    /**
     * @param tree tree
     * @return  return return
     */
    public static List<CheckParentIdDto> treeToList(List<CheckParentIdDto> tree) {
        ArrayList<CheckParentIdDto> list = new ArrayList<>();
        treeToList(tree, list);
        return list;
    }


    /**
     * tree转换为list
     *
     * @param tree
     * @return  return
     */
    private static void treeToList(List<CheckParentIdDto> tree, List<CheckParentIdDto> result) {
        for (CheckParentIdDto node : tree) {
            //读取到根节点时，将数据放入到list中
            CheckParentIdDto tag = new CheckParentIdDto();
            tag.setId(node.getId());
            tag.setParentId(node.getParentId());
            tag.setLevel(node.getLevel());
            result.add(tag);
            List<CheckParentIdDto> children = node.getChildren();
            //递归出口，节点不为空时，一直去遍历
            if (!CollectionUtils.isEmpty(children))
                treeToList(children, result);
        }
    }

    //获取等级 根据等级差距 获取数据的 parentId是否于 要新增的数据 的id相同
    private static boolean checkIsOk(Map<Long, CheckParentIdDto> map, CheckParentIdDto dto){
        boolean re = false;
        CheckParentIdDto parentIdDto = map.get(dto.getParentId());
        CheckParentIdDto idDto = map.get(dto.getId());

        int differ = parentIdDto.getLevel() - idDto.getLevel();
        if (differ > 0) {
            int a = 0;
            while (differ > a) {
                dto = map.get(dto.getParentId());
                a++;
            }
            if (idDto.getId().compareTo(dto.getParentId()) != 0) {
                re = true;//可以新增
            }
        } else {
            re = true;//可以新增
        }
        return re;
    }

}
