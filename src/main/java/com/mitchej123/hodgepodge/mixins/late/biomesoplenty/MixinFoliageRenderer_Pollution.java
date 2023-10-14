package com.mitchej123.hodgepodge.mixins.late.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mitchej123.hodgepodge.Common;
import com.mitchej123.hodgepodge.util.ColorOverrideType;

import biomesoplenty.client.render.blocks.FoliageRenderer;

@Mixin(FoliageRenderer.class)
public class MixinFoliageRenderer_Pollution {

    @ModifyExpressionValue(
            method = "renderCrossedSquares",
            remap = false,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;func_149720_d(Lnet/minecraft/world/IBlockAccess;III)I",
                    remap = false))
    private int hodgepodge$pollutionCrossedSquares(int color, Block block, int blockX, int blockY, int blockZ,
            RenderBlocks renderer) {
        ColorOverrideType type = Common.config.blockVine.matchesID(block);
        if (type == null) return color;
        return type.getColor(color, blockX, blockZ);
    }
}
