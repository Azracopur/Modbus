package ModbusClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	private Properties properties;
	private final String configFilePath="config.properties";
	
	public ConfigManager() {
		properties=new Properties();
		loadConfig();
		
	}
	private void loadConfig() {
		try(FileInputStream input=new FileInputStream(configFilePath)) {
			properties.load(input);
			
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			System.out.println("aaaaaaa");
		}
		
		
	}
	public void saveConfig(String ip,int port,int slaveid) {
		properties.setProperty("ip", ip);
		properties.setProperty("port", String.valueOf(port));
		properties.setProperty("slave_id", String.valueOf(slaveid));
		//System.out.println("Savin IP" +ip+"Saving Port"+ port+"Saving Slave ID"+slaveid);
		try(FileOutputStream output=new FileOutputStream(configFilePath)){
			properties.store(output, null);
		} catch (IOException io) {
			// TODO Auto-generated catch block
			io.printStackTrace();
		}
		
	}
	public String getIp() {
		return properties.getProperty("ip", "");
		
	}
	public int getPort() {
		return Integer.parseInt(properties.getProperty("port","0"));
				}
	public int getSlaveid() {
	   int slaveid= Integer.parseInt(properties.getProperty("slave_id","0"));
		
		return slaveid;
	}

}
