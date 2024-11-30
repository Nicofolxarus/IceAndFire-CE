package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.*;
import com.iafenvoy.iceandfire.recipe.DragonForgeRecipe;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public final class IafRecipes {
    public static final DeferredRegister<RecipeType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.RECIPE_TYPE);
    public static final RegistrySupplier<RecipeType<DragonForgeRecipe>> DRAGON_FORGE_TYPE = REGISTRY.register("dragonforge", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return "dragonforge";
        }
    });

    public static void registerDispenser() {
        DispenserBlock.registerBehavior(IafItems.STYMPHALIAN_ARROW.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                EntityStymphalianArrow entityarrow = new EntityStymphalianArrow(IafEntities.STYMPHALIAN_ARROW.get(), worldIn, position.getX(), position.getY(), position.getZ());
                entityarrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return entityarrow;
            }
        });
        DispenserBlock.registerBehavior(IafItems.AMPHITHERE_ARROW.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                EntityAmphithereArrow entityarrow = new EntityAmphithereArrow(IafEntities.AMPHITHERE_ARROW.get(), worldIn, position.getX(), position.getY(), position.getZ());
                entityarrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return entityarrow;
            }
        });
        DispenserBlock.registerBehavior(IafItems.SEA_SERPENT_ARROW.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                EntitySeaSerpentArrow entityarrow = new EntitySeaSerpentArrow(IafEntities.SEA_SERPENT_ARROW.get(), worldIn, position.getX(), position.getY(), position.getZ());
                entityarrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return entityarrow;
            }
        });
        DispenserBlock.registerBehavior(IafItems.DRAGONBONE_ARROW.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                EntityDragonArrow entityarrow = new EntityDragonArrow(IafEntities.DRAGON_ARROW.get(), position.getX(), position.getY(), position.getZ(), worldIn);
                entityarrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return entityarrow;
            }
        });
        DispenserBlock.registerBehavior(IafItems.HYDRA_ARROW.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                EntityHydraArrow entityarrow = new EntityHydraArrow(IafEntities.HYDRA_ARROW.get(), worldIn, position.getX(), position.getY(), position.getZ());
                entityarrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return entityarrow;
            }
        });
        DispenserBlock.registerBehavior(IafItems.HIPPOGRYPH_EGG.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                return new EntityHippogryphEgg(IafEntities.HIPPOGRYPH_EGG.get(), worldIn, position.getX(), position.getY(), position.getZ(), stackIn);
            }
        });
        DispenserBlock.registerBehavior(IafItems.ROTTEN_EGG.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                return new EntityCockatriceEgg(IafEntities.COCKATRICE_EGG.get(), position.getX(), position.getY(), position.getZ(), worldIn);
            }
        });
        DispenserBlock.registerBehavior(IafItems.DEATHWORM_EGG.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                return new EntityDeathWormEgg(IafEntities.DEATH_WORM_EGG.get(), position.getX(), position.getY(), position.getZ(), worldIn, false);
            }
        });
        DispenserBlock.registerBehavior(IafItems.DEATHWORM_EGG_GIGANTIC.get(), new ProjectileDispenserBehavior() {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            @Override
            protected ProjectileEntity createProjectile(World worldIn, Position position, ItemStack stackIn) {
                return new EntityDeathWormEgg(IafEntities.DEATH_WORM_EGG.get(), position.getX(), position.getY(), position.getZ(), worldIn, true);
            }
        });
        BrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, IafItems.SHINY_SCALES.get(), Potions.WATER_BREATHING);
    }

    public static void init() {
        registerDispenser();
    }
}
