package me.diabolicatrix.gui;

import java.util.List;

import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;

public class GuiScrollingList extends GuiSlot
{
    public List list;

    public GuiScrollingList(List list, Minecraft mc, int width, int height, int top, int bottom, int slotHeight, int left)
    {
        super(mc, width, height, top, bottom, mc.fontRendererObj.FONT_HEIGHT + 1);
        this.left = left;
        this.right = this.left + this.width;
        this.list = list;
    }

    protected int getSize()
    {
        return this.list.size();
    }

    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}

    protected boolean isSelected(int slotIndex)
    {
        return false;
    }

    protected void drawBackground() {}

    protected void drawSlot(int entryID, int left, int height, int scrollBarWidth, int mouseXIn, int mouseYIn)
    {
        if(height > (56 - 9) && height < 150)
        {
            this.mc.fontRendererObj.drawString((String) this.list.get(entryID), this.left + 2, height, 0xFFFFFF);
        }
    }

    protected int getScrollBarX()
    {
        return this.right - 6;
    }

    @Override
    protected void drawContainerBackground(Tessellator tessellator)
    {

    }

    @Override
    protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha) {}

    @Override
    public void drawScreen(int mouseXIn, int mouseYIn, float p_148128_3_)
    {
        this.mouseX = mouseXIn;
        this.mouseY = mouseYIn;

        this.drawBackground();

        int scrollBarX = this.getScrollBarX();
        int scrollBarPos = scrollBarX + 6;

        this.bindAmountScrolled();

        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        this.drawContainerBackground(tessellator);

        int selectionX = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
        int selectionY = this.top + 4 - (int)this.amountScrolled;
        
        this.drawSelectionBox(selectionX, selectionY, mouseXIn, mouseYIn);

        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.disableTexture2D();

        int var = this.func_148135_f();

        if(var > 0)
        {
            int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
            k1 = MathHelper.clamp_int(k1, 32, this.bottom - this.top - 8);
            int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / var + this.top;

            if(l1 < this.top)
            {
                l1 = this.top;
            }

            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)scrollBarX, (double)this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos((double)scrollBarPos, (double)this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos((double)scrollBarPos, (double)this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            worldrenderer.pos((double)scrollBarX, (double)this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)scrollBarX, (double)(l1 + k1), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
            worldrenderer.pos((double)scrollBarPos, (double)(l1 + k1), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
            worldrenderer.pos((double)scrollBarPos, (double)l1, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
            worldrenderer.pos((double)scrollBarX, (double)l1, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
            tessellator.draw();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)scrollBarX, (double)(l1 + k1 - 1), 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 255).endVertex();
            worldrenderer.pos((double)(scrollBarPos - 1), (double)(l1 + k1 - 1), 0.0D).tex(1.0D, 1.0D).color(192, 192, 192, 255).endVertex();
            worldrenderer.pos((double)(scrollBarPos - 1), (double)l1, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 255).endVertex();
            worldrenderer.pos((double)scrollBarX, (double)l1, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 255).endVertex();
            tessellator.draw();
        }

        this.func_148142_b(mouseXIn, mouseYIn);
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        
    }
}
