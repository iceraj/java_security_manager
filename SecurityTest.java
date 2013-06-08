/*
 * Created on Feb 4, 2004
 *
 * Copyright iceraj @ interwoven.com 
 * All Rights Reserved
 * 
 * @author iceraj
 *  
 */

/**
 * SecurityTest is example class. It illustrates use of SecurityManager to prevent legacy code from exiting parent JVM.
 *   
 *  * @author iceraj
 */
public class SecurityTest
{

	public static void main(String[] args)
	{
		SecurityManager origSM = System.getSecurityManager() ;
		System.out.println( origSM ) ;
		SecurityManager mySM = new SecurityManager() {
			public void checkExit( int status )
			{
				throw new SecurityException( "Class not allowed to invoke System.exit(" + status + ") " ) ;
			}
			
			public void checkPermission( java.security.Permission p )
			{
				System.out.println( "Security Manager permit:" + p ) ;
			}
		} ;
		System.setSecurityManager( mySM ) ;
		try
		{
			System.out.println( "myCode" ) ;
			if( true )
			{
				System.exit( 1 ) ;
			}
			System.out.println( "rest of my Code, after exception" ) ;
		}
		catch( SecurityException e )
		{
			e.printStackTrace() ;
		}
		finally
		{
			System.out.println( "executed always" ) ;
		}
		System.setSecurityManager( origSM ) ;	
	}
}
