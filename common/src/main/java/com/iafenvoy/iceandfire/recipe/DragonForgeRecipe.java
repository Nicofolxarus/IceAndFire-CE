package com.iafenvoy.iceandfire.recipe;

import com.iafenvoy.iceandfire.entity.block.BlockEntityDragonForge;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafRecipeSerializers;
import com.iafenvoy.iceandfire.registry.IafRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DragonForgeRecipe implements Recipe<BlockEntityDragonForge> {
    private final Identifier id;
    private final Ingredient input;
    private final Ingredient blood;
    private final ItemStack result;
    private final String dragonType;
    private final int cookTime;

    public DragonForgeRecipe(Identifier id, Ingredient input, Ingredient blood, ItemStack result, String dragonType, int cookTime) {
        this.id = id;
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
    public boolean matches(BlockEntityDragonForge inv, World worldIn) {
        return this.input.test(inv.getStack(0)) && this.blood.test(inv.getStack(1)) && this.dragonType.equals(inv.getTypeID());
    }

    public boolean isValidInput(ItemStack stack) {
        return this.input.test(stack);
    }

    public boolean isValidBlood(ItemStack blood) {
        return this.blood.test(blood);
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryAccess) {
        return this.result;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ItemStack craft(BlockEntityDragonForge dragonforge, DynamicRegistryManager registryAccess) {
        return this.result;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
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

    public Identifier getId() {
        return this.id;
    }

    public static class Serializer implements RecipeSerializer<DragonForgeRecipe> {
        @Override
        public DragonForgeRecipe read(PacketByteBuf buffer) {
            Identifier id = buffer.readIdentifier();
            int cookTime = buffer.readInt();
            String dragonType = buffer.readString();
            Ingredient input = Ingredient.fromPacket(buffer);
            Ingredient blood = Ingredient.fromPacket(buffer);
            ItemStack result = buffer.readItemStack();
            return new DragonForgeRecipe(id, input, blood, result, dragonType, cookTime);
        }

        @Override
        public Codec<DragonForgeRecipe> codec() {
            return RecordCodecBuilder.create(i -> i.group(
                    Identifier.CODEC.fieldOf("id").forGetter(DragonForgeRecipe::getId),
                    Ingredient.ALLOW_EMPTY_CODEC.fieldOf("input").forGetter(DragonForgeRecipe::getInput),
                    Ingredient.ALLOW_EMPTY_CODEC.fieldOf("blood").forGetter(DragonForgeRecipe::getBlood),
                    ItemStack.CODEC.fieldOf("result").forGetter(DragonForgeRecipe::getResultItem),
                    Codec.STRING.fieldOf("dragonType").forGetter(DragonForgeRecipe::getDragonType),
                    Codec.INT.fieldOf("cookTime").forGetter(DragonForgeRecipe::getCookTime)
            ).apply(i, DragonForgeRecipe::new));
        }

        @Override
        public void write(PacketByteBuf buffer, DragonForgeRecipe recipe) {
            buffer.writeIdentifier(recipe.id);
            buffer.writeInt(recipe.cookTime);
            buffer.writeString(recipe.dragonType);
            recipe.input.write(buffer);
            recipe.blood.write(buffer);
            buffer.writeItemStack(recipe.result);
        }
    }
}
