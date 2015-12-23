package ar.edu.unq.games.vainillautils;


public class AnimationRotateMovedLowCost extends AnimationRotateMoved  {

	SpriteMoved[] sprites000;
	SpriteMoved[] sprites025;
	SpriteMoved[] sprites050;
	SpriteMoved[] sprites075;
	SpriteMoved[] sprites100;
	SpriteMoved[] sprites125;
	SpriteMoved[] sprites150;
	SpriteMoved[] sprites175;
	SpriteMoved[] sprites200;
	SpriteMoved[] sprites225;
	SpriteMoved[] sprites250;
	SpriteMoved[] sprites275;
	SpriteMoved[] sprites300;
	SpriteMoved[] sprites325;
	SpriteMoved[] sprites350;
	SpriteMoved[] sprites375;
	SpriteMoved[] sprites400;
	SpriteMoved[] sprites425;
	SpriteMoved[] sprites450;
	SpriteMoved[] sprites475;
	SpriteMoved[] sprites500;
	SpriteMoved[] sprites525;
	SpriteMoved[] sprites550;
	SpriteMoved[] sprites575;
	SpriteMoved[] sprites600;
	SpriteMoved[] sprites625;
	
	public AnimationRotateMovedLowCost(double meantime, SpriteMoved[] sprites) {
		super(meantime, sprites);
		this.sprites000 = this.getSpritesIniciales();
		super.rotate(0.25);
		this.sprites025 = (SpriteMoved[]) this.getSprites();
		super.rotate(0.50);
		this.sprites050 = (SpriteMoved[]) this.getSprites();
		super.rotate(0.75);
		this.sprites075 = (SpriteMoved[]) this.getSprites();
		super.rotate(1);
		this.sprites100 = (SpriteMoved[]) this.getSprites();
		super.rotate(1.25);
		this.sprites125 = (SpriteMoved[]) this.getSprites();
		super.rotate(1.50);
		this.sprites150 = (SpriteMoved[]) this.getSprites();
		super.rotate(1.75);
		this.sprites175 = (SpriteMoved[]) this.getSprites();
		super.rotate(2);
		this.sprites200 = (SpriteMoved[]) this.getSprites();
		super.rotate(2.25);
		this.sprites225 = (SpriteMoved[]) this.getSprites();
		super.rotate(2.50);
		this.sprites250 = (SpriteMoved[]) this.getSprites();
		super.rotate(2.75);
		this.sprites275 = (SpriteMoved[]) this.getSprites();
		super.rotate(3);
		this.sprites300 = (SpriteMoved[]) this.getSprites();
		super.rotate(3.25);
		this.sprites325 = (SpriteMoved[]) this.getSprites();
		super.rotate(3.50);
		this.sprites350 = (SpriteMoved[]) this.getSprites();
		super.rotate(3.75);
		this.sprites375 = (SpriteMoved[]) this.getSprites();
		super.rotate(4);
		this.sprites400 = (SpriteMoved[]) this.getSprites();
		super.rotate(4.25);
		this.sprites425 = (SpriteMoved[]) this.getSprites();
		super.rotate(4.50);
		this.sprites450 = (SpriteMoved[]) this.getSprites();
		super.rotate(4.75);
		this.sprites475 = (SpriteMoved[]) this.getSprites();
		super.rotate(5);
		this.sprites500 = (SpriteMoved[]) this.getSprites();
		super.rotate(5.25);
		this.sprites525 = (SpriteMoved[]) this.getSprites();
		super.rotate(5.50);
		this.sprites550 = (SpriteMoved[]) this.getSprites();
		super.rotate(5.75);
		this.sprites575 = (SpriteMoved[]) this.getSprites();
		super.rotate(6);
		this.sprites600 = (SpriteMoved[]) this.getSprites();
		super.rotate(6.25);
		this.sprites625 = (SpriteMoved[]) this.getSprites();
	}
	
	public SpriteMoved[] imageRotated(double radians){
		
		if(radians < 0.13 ){
			return this.sprites000;
		}
		else{
			if(radians < 0.37){
				return this.sprites025;
			}
			else{
				if(radians < 0.63){
					return this.sprites050;
				}
				else{
					if(radians < 0.87){
						return this.sprites075;
					}
					else{
						if(radians < 1.13){
							return this.sprites100;
						}
						else{
							if(radians < 1.37){
								return this.sprites125;
							}
							else{
								if(radians < 1.63){
									return this.sprites150;
								}
								else{
									if(radians < 1.87){
										return this.sprites175;
									}
									else{
										if(radians < 2.13){
											return this.sprites200;
										}
										else{
											if(radians < 2.37){
												return this.sprites225;
											}
											else{
												if(radians < 2.63){
													return this.sprites250;
												}
												else{
													if(radians < 2.87){
														return this.sprites275;
													}
													else{
														if(radians < 3.13){
															return this.sprites300;
														}
														else{
															if(radians < 3.37){
																return this.sprites325;
															}
															else{
																if(radians < 3.63){
																	return this.sprites350;
																}
																else{
																	if(radians < 3.87){
																		return this.sprites375;
																	}
																	else{
																		if(radians < 4.13){
																			return this.sprites400;
																		}
																		else{
																			if(radians < 4.37){
																				return this.sprites425;
																			}
																			else{
																				if(radians < 4.63){
																					return this.sprites450;
																				}
																				else{
																					if(radians < 4.87){
																						return this.sprites475;
																					}
																					else{
																						if(radians < 5.13){
																							return this.sprites500;
																						}
																						else{
																							if(radians < 5.37){
																								return this.sprites525;
																							}
																							else{
																								if(radians < 5.63){
																									return this.sprites550;
																								}
																								else{
																									if(radians < 5.87){
																										return this.sprites575;
																									}
																									else{
																										if(radians < 6.13){
																											return this.sprites600;
																										}
																										else{

																												return this.sprites625;
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}	
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}								
				}
			}
		
	}

}
