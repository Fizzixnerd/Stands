package com.fizzixnerd.stands

import net.minecraft.client.renderer.entity.{Render, RenderManager}
import net.minecraft.entity.Entity
import net.minecraftforge.fml.client.registry.IRenderFactory

class RenderFactoryTheWorld extends IRenderFactory[EntityTheWorld] {
  override
  def createRenderFor(manager: RenderManager): Render[_ >: EntityTheWorld <: Entity] = {
    new RenderTheWorld(manager)
  }
}