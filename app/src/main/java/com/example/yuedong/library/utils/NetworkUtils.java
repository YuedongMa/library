package com.example.yuedong.library.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.example.yuedong.library.MainApplication;

import java.util.Locale;

/**
 * 网络工具类
 *
 * Created by Administrator on 2017/9/6.
 */

public class NetworkUtils {

	public static enum NetType {
		WIFI, CMNET, CMWAP, NONE
	}

	/**
	 * 判断是否有网络连接
	 *
	 * @return
	 */
	public static boolean isNetworkConnected() {
		if (MainApplication.getContext() != null) {
			// 获取手机所有连接管理对象( 包括对 wi-fi,net 等连接的管理 )
			ConnectivityManager manager = (ConnectivityManager) MainApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null) {
				return networkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断网络是否可用
	 *
	 * 有时会出现 wifi 显示连接，但无法上网问题
	 *
	 * @return
	 */
	public static boolean isNetworkOnline() {
		try {
			String ip = "www.baidu.com";
			Process p = Runtime.getRuntime().exec("ping -c 1 -w 100 " + ip);// ping3次
			// PING 的状态
			int status = p.waitFor();
			if (status == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 *
	 * @return
	 */
	public static boolean isMobileConnected() {
		if (MainApplication.getContext() != null) {
			ConnectivityManager manager = (ConnectivityManager) MainApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
				return networkInfo.isAvailable();
		}
		return false;
	}
	/**
	 * 获取当前网络连接的类型信息
	 *
	 * @param context
	 * @return
	 */    public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				return networkInfo.getType();
			}
		}
		return -1;
	}
	/**
	 * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
	 *
	 * 自定义
	 * @return
	 */
	public static int getAPNType() {
		int netType = 0;
		ConnectivityManager manager = (ConnectivityManager) MainApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = 1;
		} else if (nType == ConnectivityManager.TYPE_MOBILE) {
			int nSubType = networkInfo.getSubtype();
			TelephonyManager telephonyManager = (TelephonyManager) MainApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
			//3G   联通的3G为UMTS或HSDPA 电信的3G为EVDO
			if (nSubType == TelephonyManager.NETWORK_TYPE_LTE
					&& !telephonyManager.isNetworkRoaming()) {
				netType = 4;
			} else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
					|| nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
					|| nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0
					&& !telephonyManager.isNetworkRoaming()) {
				netType = 3;
				//2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
			} else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
					|| nSubType == TelephonyManager.NETWORK_TYPE_EDGE
					|| nSubType == TelephonyManager.NETWORK_TYPE_CDMA
					&& !telephonyManager.isNetworkRoaming()) {
				netType = 2;
			} else {
				netType = 2;
			}
		}
		return netType;
	}

	public static String getNetType(){
		String str="无网络";
		switch (getAPNType()){
			case 0:
				str="没有网络";
				break;
			case 1:
				str="WIFI网络";
				break;
			case 4:
				str="4G网络";
				break;
			case 3:
				str="3G网络";
				break;
			default:
				str="2G网络";
				break;
		}
		return str;
	}

	/**
	 * 判断 GPS 是否打开
	 * ACCESS_FINE_LOCATION权限
	 *
	 * @return
	 */
	public static boolean isGPSEnabled() {
		LocationManager locationManager = ((LocationManager) MainApplication.getContext().getSystemService(Context.LOCATION_SERVICE));
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * 判断当前是否有网络连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetAcailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
		for(int i = 0; i < networkInfos.length; i ++){
			if(networkInfos[i].getState() == NetworkInfo.State.CONNECTED){
				return true;
			}
		}
		return false;
	}

	public static NetType getNetType(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if(networkInfo == null){
			return NetType.NONE;
		}
		int netType = networkInfo.getType();
		if(netType == ConnectivityManager.TYPE_MOBILE){
			if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")) {
				return NetType.CMNET;
			} else {
				return NetType.CMWAP;
			}
		} else if(netType == ConnectivityManager.TYPE_WIFI){
			return NetType.WIFI;
		}
		return NetType.NONE;
	}

	/**
	 * 检查是否是 wifi 网络
	 *
	 * @return
	 */
	public static boolean isWifiConnected() {
		int mNetType = NetworkUtils.getAPNType();
		if(mNetType != 1 && mNetType != 0){
			return false;
		}
		return true;
	}

}
