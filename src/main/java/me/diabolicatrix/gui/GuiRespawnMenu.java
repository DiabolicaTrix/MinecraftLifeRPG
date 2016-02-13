package me.diabolicatrix.gui;

import me.diabolicatrix.other.PlayerEEP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class GuiRespawnMenu extends GuiScreen
{
    private EntityPlayer player;

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
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, 0, 0, "Ok (tmp)"));
    }
    
    public GuiRespawnMenu(EntityPlayer player)
    {
        this.player = player;
    }
    
    @Override
    protected void actionPerformed(GuiButton btn) {
        switch(btn.id)
        {
            case 0:
                this.mc.thePlayer.respawnPlayer();
                this.mc.displayGuiScreen((GuiScreen)null);
                //PlayerEEP props = PlayerEEP.get(player);
                //props.loadProxyData(player);
                break;
        }
    }

}
