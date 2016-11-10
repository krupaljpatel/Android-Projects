/**
 * The pauseButton
 * 
 * @author Lars Harmsen
 * Copyright (c) <2014> <Lars Harmsen - Quditcode>
 */

package com.Quditcode.flappytrump.sprites;

import com.Quditcode.flappytrump.Game;
import com.Quditcode.flappytrump.GameView;
import com.Quditcode.flappytrump.R;
import com.Quditcode.flappytrump.Util;

public class PauseButton extends Sprite{
    public PauseButton(GameView view, Game game) {
        super(view, game);
        this.bitmap = Util.getScaledBitmapAlpha8(game, R.drawable.pause_button);
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }
    
    /**
     * Sets the button in the right upper corner.
     */
    @Override
    public void move(){
        this.x = this.view.getWidth() - this.width;
        this.y = 0;
    }
}