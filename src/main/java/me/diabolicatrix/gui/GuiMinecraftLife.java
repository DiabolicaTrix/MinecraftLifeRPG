package me.diabolicatrix.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;

public class GuiMinecraftLife extends GuiUtils
{
    public void drawMinecraftLifeBackground(double startX, double startY, double endX, double endY)
    {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            
            this.startDrawing();
            
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(startX, startY, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 240).endVertex();
            worldrenderer.pos(startX, endY, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 240).endVertex();
            worldrenderer.pos(endX, endY, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 240).endVertex();
            worldrenderer.pos(endX, startY, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 240).endVertex();
            tessellator.draw();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(startX, startY, 0.0D).tex(0.0D, 1.0D).color(157, 107, 20, 255).endVertex();
            worldrenderer.pos(startX, startY + 11.0D, 0.0D).tex(1.0D, 1.0D).color(157, 107, 20, 255).endVertex();
            worldrenderer.pos(endX, startY + 11.0D, 0.0D).tex(1.0D, 0.0D).color(157, 107, 20, 255).endVertex();
            worldrenderer.pos(endX, startY, 0.0D).tex(0.0D, 0.0D).color(157, 107, 20, 255).endVertex();
            tessellator.draw();
            
            this.stopDrawing();
    }
    
    public void drawOrangeRect(double startX, double startY, double endX, double endY)
    {
        
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            
            this.startDrawing();
            
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(startX, startY, 0.0D).tex(0.0D, 1.0D).color(157, 107, 20, 255).endVertex();
            worldrenderer.pos(startX, startY + 11.0D, 0.0D).tex(1.0D, 1.0D).color(157, 107, 20, 255).endVertex();
            worldrenderer.pos(endX, startY + 11.0D, 0.0D).tex(1.0D, 0.0D).color(157, 107, 20, 255).endVertex();
            worldrenderer.pos(endX, startY, 0.0D).tex(0.0D, 0.0D).color(157, 107, 20, 255).endVertex();
            tessellator.draw();

            
            this.stopDrawing();
    }
    
    public void drawBlackRect(double startX, double startY, double endX, double endY)
    {
        
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            
            this.startDrawing();
            
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(startX, startY, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(startX, startY + 11.0D, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(endX, startY + 11.0D, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos(endX, startY, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();

            
            this.stopDrawing();
    }
}
