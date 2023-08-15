package com.tom.vivecraftcompat.mixin.compat.create;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.vivecraft.client_vr.ClientDataHolderVR;

import net.minecraft.world.phys.Vec3;

import com.simibubi.create.content.trains.track.TrackBlockOutline;

import com.tom.vivecraftcompat.VRMode;

@Mixin(TrackBlockOutline.class)
public class CreateTrackBlockOutlineMixin {
	private static final ClientDataHolderVR DATA_HOLDER = ClientDataHolderVR.getInstance();

	@ModifyVariable(at = @At("STORE"), method = "pickCurves()V", remap = false, ordinal = 0)
	private static Vec3 vrOrigin(Vec3 original) {
		if (VRMode.isVR() && !DATA_HOLDER.vrSettings.seated)
			return DATA_HOLDER.vrPlayer.vrdata_world_render.getController(0).getPosition();
		return original;
	}
}
