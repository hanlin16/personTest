package javabasic.digui;

import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void choose(List<LabelIdTreeITO> originIds) {
        //平台标签子标签的父标签map
        Map<Long, Long> originMap = new HashMap<>();
        //租户标签的原标签与现标签map
        Map<Long, Long> currentMap = new HashMap<>();
        for (LabelIdTreeITO ito : originIds) {
            ergodic(ito, originMap, ito.getId());
        }

        System.out.println(originMap);

    }

    /**
     * 平台标签子标签的父标签map
     */
    public static void ergodic(LabelIdTreeITO ito, Map<Long, Long> map, Long treeId) {
        if (CollectionUtils.isEmpty(ito.getSubIds())) {
            return;
        }
        for (LabelIdTreeITO vo : ito.getSubIds()) {
            map.put(vo.getId(), treeId);
            ergodic(vo, map, vo.getId());
        }

    }

    public static void main(String[] args) {
        Long id = 5L;
        List<LabelIdTreeITO> originIds = new ArrayList<>();
        for (Long i = 0L; i < 5; i++) {
            List<LabelIdTreeITO> subList = new ArrayList<>();
            for (Long j = 0L; j < 5; j++) {
                subList.add(new LabelIdTreeITO(id++));
            }
            originIds.add(new LabelIdTreeITO(i, subList));
        }

        choose(originIds);

        System.out.println("结束.....");

    }

}
