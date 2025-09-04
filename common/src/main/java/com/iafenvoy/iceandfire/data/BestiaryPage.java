package com.iafenvoy.iceandfire.data;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.item.BestiaryItem;
import com.iafenvoy.iceandfire.registry.IafDataComponents;
import com.iafenvoy.iceandfire.registry.IafRegistries;
import net.minecraft.item.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public record BestiaryPage(String name, int pageCount) {
    public static Set<BestiaryPage> containedPages(Collection<String> pages) {
        return pages.stream().map(IceAndFire::id).map(IafRegistries.BESTIARY_PAGE::get).collect(Collectors.toSet());
    }

    public static List<BestiaryPage> possiblePages(ItemStack book) {
        if (book.getItem() instanceof BestiaryItem) {
            List<String> s = book.get(IafDataComponents.BESTIARY_PAGES.get());
            if (s == null) return List.of();
            Collection<BestiaryPage> containedPages = containedPages(s);
            List<BestiaryPage> possiblePages = IafRegistries.BESTIARY_PAGE.stream().collect(Collectors.toList());
            possiblePages.removeAll(containedPages);
            return possiblePages;
        }
        return Collections.emptyList();
    }

    public static void addPage(BestiaryPage page, ItemStack book) {
        if (book.getItem() instanceof BestiaryItem) {
            List<String> already = book.get(IafDataComponents.BESTIARY_PAGES.get());
            if (already == null) return;
            if (!already.contains(page.name)) {
                already = new LinkedList<>(already);// mutable copy
                already.add(page.name);
                book.set(IafDataComponents.BESTIARY_PAGES.get(), already);
            }
        }
    }
}
