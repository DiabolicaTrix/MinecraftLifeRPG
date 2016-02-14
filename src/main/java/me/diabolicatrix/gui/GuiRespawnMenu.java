package me.diabolicatrix.gui;

import me.diabolicatrix.proxy.CommonProxy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class GuiRespawnMenu extends GuiScreen
{
    private EntityPlayer player;
    private int enableButtonsTimer;

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        this.drawDefaultBackground();
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
        System.out.println(CommonProxy.extendedEntityData);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, 0, 0, "Ok (tmp)"));
        for (GuiButton guibutton : this.buttonList)
        {
            guibutton.enabled = false;
        }
    }

    public GuiRespawnMenu(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    protected void actionPerformed(GuiButton btn)
    {
        switch(btn.id)
        {
            case 0:
                this.mc.thePlayer.respawnPlayer();
                this.mc.displayGuiScreen((GuiScreen)null);
                
                break;
        }
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ++this.enableButtonsTimer;

        if (this.enableButtonsTimer == 20)
        {
            for (GuiButton guibutton : this.buttonList)
            {
                guibutton.enabled = true;
            }
        }
    }

}
