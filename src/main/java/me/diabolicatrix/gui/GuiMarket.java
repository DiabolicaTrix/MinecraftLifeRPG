package me.diabolicatrix.gui;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import me.diabolicatrix.entities.EntityLifeTrader;
import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.other.PlayerEEP;
import me.diabolicatrix.packets.PacketRequestPlayerList;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiMarket extends GuiMinecraftLife
{
    
    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiWidth = 300;
        int guiHeight = 150;
        int xPos = (this.width / 2) - (guiWidth / 2);
        int yPos = (this.height / 2) - (guiHeight / 2);
        
        this.drawMinecraftLifeBackground(xPos, yPos, xPos + guiWidth, yPos + guiHeight);
        this.drawLifeRect(xPos + 25, yPos + 25, xPos + 125, yPos + 11);
        this.drawLifeRect(xPos + guiWidth - 125, yPos + 25, xPos + guiWidth - 25, yPos + 11);
        this.drawString(fontRendererObj, "Shop Inventory", (xPos + 25) + (100 / 2) - (fontRendererObj.getStringWidth("Shop Inventory") / 2), (yPos + 25) + (11 / 2) - (fontRendererObj.FONT_HEIGHT / 2), 0xFFFFFF);
        this.drawString(fontRendererObj, "My Inventory", (xPos + guiWidth - 125) + (100 / 2) - (fontRendererObj.getStringWidth("My Inventory") / 2), (yPos + 25) + (11 / 2) - (fontRendererObj.FONT_HEIGHT / 2), 0xFFFFFF);
        super.drawScreen(x, y, ticks);
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
    }

    @Override
    protected void actionPerformed(GuiButton btn)
    {
        switch(btn.id)
        {
            case 1:
                break;
            case 2:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
        }
    }
    
    @SideOnly(Side.CLIENT)
    class List extends GuiSlot
    {
        public List()
        {
            super(GuiMarket.this.mc, GuiMarket.this.width/2, GuiMarket.this.height - 22, 11, GuiMarket.this.height - 11, GuiMarket.this.fontRendererObj.FONT_HEIGHT + 1);
            this.left = GuiMarket.this.width / 2;
            this.right = GuiMarket.this.width;
        }

        protected int getSize()
        {
            //MinecraftLifeRPG.network.sendToServer(new PacketRequestPlayerList());
            return 35;
            //return ClientProxy.getPlayerList().length;
        }

        /**
         * The element in the slot that was clicked, boolean for whether it was double clicked or not
         */
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
        {
        }

        /**
         * Returns true if the element passed in is currently selected
         */
        protected boolean isSelected(int slotIndex)
        {
            return false;
        }

        protected void drawBackground()
        {
        }

        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int mouseXIn, int mouseYIn)
        {
            String[] playerList = ClientProxy.getPlayerList();
            String name = "Player" + entryID;
            if(name.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getDisplayNameString()))
            {
                GuiMarket.this.fontRendererObj.drawString(name, GuiMarket.this.width - this.width + 4, p_180791_3_, 0xFFFFFF);
            }
            else
            {
                GuiMarket.this.fontRendererObj.drawString(name, GuiMarket.this.width - this.width + 4, p_180791_3_, 0x37fb32);
            }
        }

        protected int getScrollBarX()
        {
            return GuiMarket.this.width - 6;
        }
        
        @Override
        protected void drawContainerBackground(Tessellator tessellator)
        {   
            
        }
        
        @Override
        protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha)
        {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);
            GlStateManager.disableTexture2D();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)this.left, (double)endY, 0.0D).tex(0.0D, (double)((float)endY / 32.0F)).color(64, 64, 64, endAlpha).endVertex();
            worldrenderer.pos((double)(this.left + this.width), (double)endY, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)endY / 32.0F)).color(64, 64, 64, endAlpha).endVertex();
            worldrenderer.pos((double)(this.left + this.width), (double)startY, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)startY / 32.0F)).color(64, 64, 64, startAlpha).endVertex();
            worldrenderer.pos((double)this.left, (double)startY, 0.0D).tex(0.0D, (double)((float)startY / 32.0F)).color(64, 64, 64, startAlpha).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
        
        @Override
        public void drawScreen(int mouseXIn, int mouseYIn, float p_148128_3_)
        {
            if (this.field_178041_q)
            {
                this.mouseX = mouseXIn;
                this.mouseY = mouseYIn;
                this.drawBackground();
                int i = this.getScrollBarX();
                int j = i + 6;
                this.bindAmountScrolled();
                GlStateManager.disableLighting();
                GlStateManager.disableFog();
                Tessellator tessellator = Tessellator.getInstance();
                WorldRenderer worldrenderer = tessellator.getWorldRenderer();
                // Forge: background rendering moved into separate method.
                this.drawContainerBackground(tessellator);
                int k = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
                int l = this.top + 4 - (int)this.amountScrolled;

                if (this.hasListHeader)
                {
                    this.drawListHeader(k, l, tessellator);
                }

                GlStateManager.disableDepth();
                int i1 = 4;
                this.overlayBackground(0, this.top, 255, 255);
                //this.overlayBackground(this.bottom, this.height, 255, 255);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableAlpha();
                GlStateManager.shadeModel(7425);
                GlStateManager.disableTexture2D();
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(0.0D, (double)GuiMarket.this.height, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiMarket.this.width, (double)GuiMarket.this.height, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiMarket.this.width, 0.0D, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                
                GlStateManager.enableTexture2D();
                GlStateManager.shadeModel(7424);
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
                
                this.drawSelectionBox(k, l, mouseXIn, mouseYIn);
                
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableAlpha();
                GlStateManager.shadeModel(7425);
                GlStateManager.disableTexture2D();
                
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 1.0D).color(157, 107, 20, 255).endVertex();
                worldrenderer.pos(0.0D, 11.0D, 0.0D).tex(1.0D, 1.0D).color(157, 107, 20, 255).endVertex();
                worldrenderer.pos((double)GuiMarket.this.width, 11.0D, 0.0D).tex(1.0D, 0.0D).color(157, 107, 20, 255).endVertex();
                worldrenderer.pos((double)GuiMarket.this.width, 0.0D, 0.0D).tex(0.0D, 0.0D).color(157, 107, 20, 255).endVertex();
                tessellator.draw();
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos(0.0D, (double)GuiMarket.this.height - 11, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(0.0D, (double)GuiMarket.this.height, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiMarket.this.width, (double)GuiMarket.this.height, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiMarket.this.width, (double)GuiMarket.this.height - 11, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
              /*  worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos((double)this.left, (double)(this.top + i1), 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 0).endVertex();
                worldrenderer.pos((double)this.right, (double)(this.top + i1), 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 0).endVertex();
                worldrenderer.pos((double)this.right, (double)this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)this.left, (double)this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos((double)this.left, (double)this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)this.right, (double)this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)this.right, (double)(this.bottom - i1), 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 0).endVertex();
                worldrenderer.pos((double)this.left, (double)(this.bottom - i1), 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 0).endVertex();
                tessellator.draw();*/
                int j1 = this.func_148135_f();

                if (j1 > 0)
                {
                    int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                    k1 = MathHelper.clamp_int(k1, 32, this.bottom - this.top - 8);
                    int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;

                    if (l1 < this.top)
                    {
                        l1 = this.top;
                    }

                    worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                    worldrenderer.pos((double)i, (double)this.bottom, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                    worldrenderer.pos((double)j, (double)this.bottom, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                    worldrenderer.pos((double)j, (double)this.top, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                    worldrenderer.pos((double)i, (double)this.top, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                    tessellator.draw();
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                    worldrenderer.pos((double)i, (double)(l1 + k1), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                    worldrenderer.pos((double)j, (double)(l1 + k1), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                    worldrenderer.pos((double)j, (double)l1, 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                    worldrenderer.pos((double)i, (double)l1, 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                    tessellator.draw();
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                    worldrenderer.pos((double)i, (double)(l1 + k1 - 1), 0.0D).tex(0.0D, 1.0D).color(192, 192, 192, 255).endVertex();
                    worldrenderer.pos((double)(j - 1), (double)(l1 + k1 - 1), 0.0D).tex(1.0D, 1.0D).color(192, 192, 192, 255).endVertex();
                    worldrenderer.pos((double)(j - 1), (double)l1, 0.0D).tex(1.0D, 0.0D).color(192, 192, 192, 255).endVertex();
                    worldrenderer.pos((double)i, (double)l1, 0.0D).tex(0.0D, 0.0D).color(192, 192, 192, 255).endVertex();
                    tessellator.draw();
                }

                this.func_148142_b(mouseXIn, mouseYIn);
                GlStateManager.enableTexture2D();
                GlStateManager.shadeModel(7424);
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
            }
        }
    }

}
