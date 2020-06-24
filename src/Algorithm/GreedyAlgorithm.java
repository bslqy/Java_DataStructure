package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台,放入map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        // 往里面放入各个电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");


        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("大连");
        hashSet5.add("杭州");

        // 加入到map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        // 创建所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("天津");
        allAreas.add("大连");
        allAreas.add("杭州");

        // 创建ArrayLis存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // maxKey，保存在一次遍历过程中能够覆盖 最多未覆盖地区对应的电台的key，如果maxKey不为null，则会加入到selects
        String maxKey = null;
        // 找到一个key，就把一个地区从allAreas减少
        while (allAreas.size() != 0) {

            //遍历电台集合，取出key
            for (String key:broadcasts.keySet()) {
                tempSet.clear();

                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);

                // 计算未被覆盖的集合的交集
                tempSet.retainAll(allAreas);
                // 如果当前集合包含的未覆盖地区集合的数量，比maxKey指向集合的地区还多,且还有包含未覆盖的地区
                // tempSet.size() > broadcasts.get(maxKey).size() 体现出贪婪，因为优先匹配能够覆盖最多地区的集合
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
            maxKey = null;

        }

        System.out.println(selects.toString());

    }
}
