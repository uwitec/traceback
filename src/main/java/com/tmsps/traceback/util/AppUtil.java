package com.tmsps.traceback.util;

//转换数值工具类

public class AppUtil
{
	//转换INT
	public static int ConvertToInt(Object O, int defaultValue) 
	{

		if (O == null)
		{
			return defaultValue;
		}

		try 
		{
			return Integer.parseInt(O.toString());
		} 
		catch (Exception e) 
		{
			return defaultValue;
		}
	}

	public static int ConvertToInt(Object O) 
	{
		return ConvertToInt(O, 0);
	}


	//转换DOUBE
	public static double ConvertToDouble(String O) 
	{

		if (O == null)
		{
			return 0.0;
		}
		try 
		{
			return Double.valueOf(O);
		} 
		catch (Exception e) 
		{
			return 0.0;
		}
	}

	//Long
	public static long ConvertToLong(Object O, long defaultValue) 
	{

		if (O == null)
		{
			return defaultValue;
		}
		try 
		{
			return Long.parseLong(O.toString());
		} 
		catch (Exception e) 
		{
			return defaultValue;
		}
	}

	public static long ConvertToLong(Object O) 
	{
		return ConvertToLong(O, 0);
	}


	//Folat
	public static float ConvertToFloat(Object O, float defaultValue) 
	{

		if (O == null)
		{
			return defaultValue;
		}
		try 
		{
			return Float.parseFloat(O.toString());
		} 
		catch (Exception e) 
		{
			return defaultValue;
		}
	}

	public static float ConvertToFloat(Object O) 
	{
		return ConvertToFloat(O, 0f);
	}



}