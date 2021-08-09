package io.github.anantharajuc.rc.domain.model;

import java.util.Arrays;

import io.github.anantharajuc.rc.infra.exception.SpringRedditException;

public enum VoteType 
{
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) 
    {
    	
    }

    public static VoteType lookup(Integer direction) 
    {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SpringRedditException("Vote not found"));
    }

    public Integer getDirection() 
    {
        return direction;
    }
}