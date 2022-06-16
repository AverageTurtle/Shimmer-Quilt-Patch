package com.lowdragmc.shimmer.platform;

import com.lowdragmc.shimmer.ForgeShimmerConfig;
import com.lowdragmc.shimmer.platform.services.IPlatformHelper;
import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.util.Mth;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import org.lwjgl.opengl.GL31;

/**
 * @author HypherionSA
 * @date 2022/06/09
 */
public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isStencilEnabled(RenderTarget target) {
        return target.isStencilEnabled();
    }

    @Override
    public boolean useCombinedDepthStencilAttachment() {
        return ForgeConfig.CLIENT.useCombinedDepthStencilAttachment.get();
    }

    @Override
    public void enableStencil(RenderTarget renderTarget) {
        renderTarget.enableStencil();
    }

    @Override
    public int getUniformBufferObjectOffset() {
        int configValue = ForgeShimmerConfig.UBO_OFFSET.get();
        if (configValue < 0) {
            return -1;
        } else {
            return Mth.clamp(configValue, 0, GL31.glGetInteger(GL31.GL_MAX_UNIFORM_BUFFER_BINDINGS) - 1);
        }
    }
}
