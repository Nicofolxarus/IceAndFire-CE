package com.iafenvoy.iceandfire.recipe;

import com.iafenvoy.iceandfire.entity.block.BlockEntityDragonForge;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafRecipeSerializers;
import com.iafenvoy.iceandfire.registry.IafRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class DragonForgeRecipe implements Recipe<BlockEntityDragonForge.DragonForgeRecipeInput> {
    private final Ingredient input;
    private final Ingredient blood;
    private final ItemStack result;
    private final String dragonType;
    private final int cookTime;

    public DragonForgeRecipe(Ingredient input, Ingredient blood, ItemStack result, String dragonType, int cookTime) {
        this.input = input;
        this.blood = blood;
        this.result = result;
        this.dragonType = dragonType;
        this.cookTime = cookTime;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public Ingredient getBlood() {
        return this.blood;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public String getDragonType() {
        return this.dragonType;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public boolean matches(BlockEntityDragonForge.DragonForgeRecipeInput inv, World worldIn) {
        return this.input.test(inv.getStack(0)) && this.blood.test(inv.getStack(1)) && this.dragonType.equals(inv.getTypeID());
    }

    @Override
    public ItemStack craft(BlockEntityDragonForge.DragonForgeRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.result;
    }

    public boolean isValidInput(ItemStack stack) {
        return this.input.test(stack);
    }

    public boolean isValidBlood(ItemStack blood) {
        return this.blood.test(blood);
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(IafBlocks.DRAGONFORGE_FIRE_CORE.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return IafRecipeSerializers.DRAGONFORGE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return IafRecipes.DRAGON_FORGE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<DragonForgeRecipe> {
        @Override
        public MapCodec<DragonForgeRecipe> codec() {
            return RecordCodecBuilder.mapCodec(i -> i.group(
                    Ingredient.ALLOW_EMPTY_CODEC.fieldOf("input").forGetter(DragonForgeRecipe::getInput),
                    Ingredient.ALLOW_EMPTY_CODEC.fieldOf("blood").forGetter(DragonForgeRecipe::getBlood),
                    ItemStack.OPTIONAL_CODEC.fieldOf("result").forGetter(DragonForgeRecipe::getResultItem),
                    Codec.STRING.fieldOf("dragonType").forGetter(DragonForgeRecipe::getDragonType),
                    Codec.INT.fieldOf("cookTime").forGetter(DragonForgeRecipe::getCookTime)
            ).apply(i, DragonForgeRecipe::new));
        }

        @Override
        public PacketCodec<RegistryByteBuf, DragonForgeRecipe> packetCodec() {
            return PacketCodec.tuple(
                    Ingredient.PACKET_CODEC, DragonForgeRecipe::getInput,
                    Ingredient.PACKET_CODEC, DragonForgeRecipe::getBlood,
                    ItemStack.PACKET_CODEC, DragonForgeRecipe::getResultItem,
                    PacketCodecs.STRING, DragonForgeRecipe::getDragonType,
                    PacketCodecs.INTEGER, DragonForgeRecipe::getCookTime,
                    DragonForgeRecipe::new
            );
        }
    }
}
