package com.amazonaws.lambda.demo;

public class BucketManager {
	
	// Should have other way of doing this.
	public static final String bucket = "batsmqp";
	
	public static final String folder = "img";
	public static final String dataFolder = "data";
	public static final String test_folder = "test_img";
	
	static String cachedResult = null;   // compute once and then retain
	
	/**
	 * Returns the folder to use, either production one (folder) or testing (test_folder).
	 */
	static String getImgFolder() {
		if (cachedResult != null) { return cachedResult; }    // won't change
		
		String test = System.getenv("lambdaTesting");
		if (test != null) {
			cachedResult = test_folder;
			System.out.println("USING TEST");
		} else {
			cachedResult = folder;
		}
		
		return cachedResult;
	}
	
	static String getDataFolder() {
		if (cachedResult != null) { return cachedResult; }    // won't change
		
		String test = System.getenv("lambdaTesting");
		if (test != null) {
			cachedResult = test_folder;
			System.out.println("USING TEST");
		} else {
			cachedResult = dataFolder;
		}
		
		return cachedResult;
	}
	
}
