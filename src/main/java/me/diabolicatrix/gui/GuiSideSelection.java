package me.diabolicatrix.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import me.diabolicatrix.mcliferpg.MinecraftLifeRPG;
import me.diabolicatrix.packets.PacketRequestPlayerList;
import me.diabolicatrix.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiSideSelection extends GuiScreen
{
    public static final int ID = 0;
    private EntityPlayer player;
    private String[] playerList;
    private GuiSideSelection.List list;
    private int side = 0;

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        //this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawRect(0, 0, this.width, this.height, 0);
        this.list.drawScreen(x, y, ticks);
        this.drawString(fontRendererObj, I18n.format("servermenu.role"), 2, 2, 0xFFFFFF);
        this.drawString(fontRendererObj, I18n.format("servermenu.side") + ":", 10, 16, 0xFFFFFF);
        super.drawScreen(x, y, ticks);
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    @Override
    public void initGui()
    {
        MinecraftLifeRPG.network.sendToServer(new PacketRequestPlayerList());
        this.playerList = ClientProxy.getPlayerList();
        this.list = new GuiSideSelection.List();
        this.buttonList.clear();
        //this.buttonList.add(new GuiButton(0, 0, 0, 100, 20, "Cool"));
        this.buttonList.add(new GuiBluforButton(0, 10, 30, I18n.format("servermenu.cop")));
        this.buttonList.add(new GuiCivilianButton(1, 10, 60, I18n.format("servermenu.civilian")));
        GuiButton btn;
        this.buttonList.add(btn = new GuiMainMenuButton(2, this.width - 65, this.height - 10, "OK", true));
        this.buttonList.add(new GuiMainMenuButton(3, 2, this.height - 10, I18n.format("servermenu.disconnect").toUpperCase(), false));
        btn.enabled = false;
    }
    
    public GuiSideSelection(EntityPlayer player)
    {
        this.player = player;
    }
    
    @Override
    public void keyTyped(char character, int keyCode)
    {
        //if(keyCode == KeyEvent.VK_ESCAPE)
        //{
        //    System.out.println("Fuck off");
        //}
    }
    
    @Override
    protected void actionPerformed(GuiButton btn) {
        switch(btn.id)
        {
            case 0:  
                this.side = 2;
                btn.enabled = false;
                buttonList.get(1).enabled = true;
                buttonList.get(2).enabled = true;
                break;
            case 1:
                this.side = 1;
                btn.enabled = false;
                buttonList.get(0).enabled = true;
                buttonList.get(2).enabled = true;
                break;
            case 2:
                if(side > 0)
                {
                    //PlayerEEP.get(player).setSide(side);
                    System.out.println("Sync");
                    //PlayerEEP.get(player).sync();
                    ClientProxy.setLoaded(true);
                    Minecraft.getMinecraft().currentScreen = (GuiScreen)null;
                    Minecraft.getMinecraft().setIngameFocus();
                }
                else
                {
                    btn.enabled = false;
                }
                break;
            case 3: 
                if(!this.mc.isIntegratedServerRunning())
                {
                    ClientProxy.setLoaded(false);
                    this.mc.theWorld.sendQuittingDisconnectingPacket();
                    this.mc.loadWorld((WorldClient)null);
                    this.mc.displayGuiScreen(new GuiMainMenu());
                }
                else
                {
                    ClientProxy.setLoaded(false);
                    this.mc.theWorld.sendQuittingDisconnectingPacket();
                    this.mc.loadWorld((WorldClient)null);
                    this.mc.displayGuiScreen(new GuiMainMenu());
                }
                break;
        }
    }
    
    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }
    
    @SideOnly(Side.CLIENT)
    class List extends GuiSlot
    {
        public List()
        {
            super(GuiSideSelection.this.mc, GuiSideSelection.this.width/2, GuiSideSelection.this.height - 22, 11, GuiSideSelection.this.height - 11, GuiSideSelection.this.fontRendererObj.FONT_HEIGHT + 1);
            this.left = GuiSideSelection.this.width / 2;
            this.right = GuiSideSelection.this.width;
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
                GuiSideSelection.this.fontRendererObj.drawString(name, GuiSideSelection.this.width - this.width + 4, p_180791_3_, 0xFFFFFF);
            }
            else
            {
                GuiSideSelection.this.fontRendererObj.drawString(name, GuiSideSelection.this.width - this.width + 4, p_180791_3_, 0x37fb32);
            }
            //if(playerList[entryID].equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getDisplayNameString()))
            //{
            //    GuiSideSelection.this.fontRendererObj.drawString(playerList[entryID], GuiSideSelection.this.width - this.width + 4, p_180791_3_, 0xFFFFFF);
            //}
            //else
            //{
            //    GuiSideSelection.this.fontRendererObj.drawString((String)playerList[entryID], GuiSideSelection.this.width - this.width + 4, p_180791_3_, 0x37fb32);
            //}
        }

        protected int getScrollBarX()
        {
            return GuiSideSelection.this.width - 6;
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
                worldrenderer.pos(0.0D, (double)GuiSideSelection.this.height, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiSideSelection.this.width, (double)GuiSideSelection.this.height, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiSideSelection.this.width, 0.0D, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
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
                worldrenderer.pos((double)GuiSideSelection.this.width, 11.0D, 0.0D).tex(1.0D, 0.0D).color(157, 107, 20, 255).endVertex();
                worldrenderer.pos((double)GuiSideSelection.this.width, 0.0D, 0.0D).tex(0.0D, 0.0D).color(157, 107, 20, 255).endVertex();
                tessellator.draw();
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos(0.0D, (double)GuiSideSelection.this.height - 11, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(0.0D, (double)GuiSideSelection.this.height, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiSideSelection.this.width, (double)GuiSideSelection.this.height, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos((double)GuiSideSelection.this.width, (double)GuiSideSelection.this.height - 11, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
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
