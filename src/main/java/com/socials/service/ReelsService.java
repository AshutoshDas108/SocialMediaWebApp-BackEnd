package com.socials.service;

import java.util.List;

import com.socials.exceptions.ReelsException;
import com.socials.exceptions.UserException;
import com.socials.models.Reels;
import com.socials.models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reels, User user );
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws ReelsException, UserException;
	
	

}
