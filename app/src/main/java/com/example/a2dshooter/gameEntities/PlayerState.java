package com.example.a2dshooter.gameEntities;

public class PlayerState {
    public enum State{
        IDLE,
        IS_MOVING,
        START_MOVING
    }

    private final Player player;
    private State state;

    public PlayerState(Player player){
        this.player = player;
        this.state = State.IDLE;
    }

    public State getState() {
        return state;
    }

    public void update(){
        switch (state){
            case IDLE:
                if(player.velocityX != 0 || player.velocityY != 0)
                    state = State.START_MOVING;
                break;
            case START_MOVING:
                if(player.velocityX !=0 || player.velocityY != 0)
                    state = State.IS_MOVING;
                break;
            case IS_MOVING:
                if(player.velocityX == 0 && player.velocityY == 0)
                    state = State.IDLE;
                break;

            default:

                break;
        }
    }
}
