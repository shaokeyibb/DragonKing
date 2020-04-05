package cn.lingyuncraft.dragonking;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Top {

    private static Top top;

    @Getter
    private LinkedHashMap<UUID, Integer> sortedMap;
    private LinkedList<UUID> linkedKeyList;

    private Top(Map<UUID, Integer> nonSortedMap) {
        sortedMap = sortMap(nonSortedMap);
        linkedKeyList = getLinkedKeyList();
    }

    public static Top get() {
        if (top == null) {
            top = new Top(Data.DATA);
        }
        return top;
    }

    public static void refresh() {
        top = new Top(Data.DATA);
    }

    private LinkedHashMap<UUID, Integer> sortMap(Map<UUID, Integer> uuidDoubleMap) {
        return uuidDoubleMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private LinkedList<UUID> getLinkedKeyList() {
        LinkedList<UUID> keyList = new LinkedList<>();
        sortedMap.forEach((uuid, aDouble) ->
                keyList.add(uuid)
        );
        return keyList;
    }

    public int getTop(UUID uuid) {
        return linkedKeyList.indexOf(uuid) + 1;
    }

    public UUID getTop(int indexTop) {
        return linkedKeyList.get(indexTop - 1);
    }
}
