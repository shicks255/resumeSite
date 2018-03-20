package com.steven.hicks.entities.store;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ItemType
{
    ALBUM("ALBUM"),
    LEGO_SET("LEGO_SET");

    String description;

    ItemType(String description)
    {
        this.description = description;
    }
    
    @Override
    public String toString() {return description;}

    public static final Map<String, ItemType> descriptionMap = Stream.of(values())
            .collect(Collectors.toMap(Object::toString, e -> e));

    public static ItemType getItemType(String type)
    {
        return descriptionMap.get(type);
    }


}
