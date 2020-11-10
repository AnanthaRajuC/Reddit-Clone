package io.github.anantharajuc.rc.api;

public class ResourcePaths 
{
	public static final String API                  = "api";
	public static final String V1                   = "/v1"; 

	public static final String ROOT_API             = "/" + API; 
	public static final String ROOT_API_V1          = ROOT_API + V1;   

	public class Authentication
	{
		public static final String NAME             = "/auth";
		
		public final class V1
		{
			private V1() {}
			
			public static final String ROOT         = ROOT_API_V1 + NAME;
			
			public static final String SIGNUP       = "/signup";
			public static final String VERIFICATION = "/verification/{token}";
			public static final String LOGIN        = "/login"; 	
		}
	}
	
	public class Subreddit
	{
		public static final String NAME             = "/subreddit";
		
		public final class V1
		{
			private V1() {}
			
			public static final String ROOT         = ROOT_API_V1 + NAME; 
		}
	}
}
