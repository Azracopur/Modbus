package ModbusClient;

import java.util.Random;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;

public class Modbus {

	public static void main(String[] args) {
			
		IpParameters ipParameters =new IpParameters(); 
		ipParameters.setHost("169.254.62.200");
		ipParameters.setPort(502);
	
		ModbusFactory modbusFactory=new ModbusFactory();
		ModbusMaster master = modbusFactory.createTcpMaster(ipParameters,true);
		try {
			master.setTimeout(1000);
			master.init();
			int slaveId=1;
			int startOffset=0;
			int numberOfRegisters=50;
			ReadCoilsRequest request= new ReadCoilsRequest(slaveId,startOffset,numberOfRegisters);
			ReadCoilsResponse response =(ReadCoilsResponse)master.send(request);
			ReadCoilsResponse readResponse =(ReadCoilsResponse) response;
			if(response.isException()) {
				System.out.println("Hata Yanıtı:" + response.getExceptionMessage());
			}
			else {
				
				
				boolean[] coilStatuses=readResponse.getBooleanData();
				for(int i=0;i<response.getBooleanData().length;i++) {
					
					System.out.println("Y"+(startOffset +i )+"="+response.getBooleanData()[i]);
					
				}

				}
			boolean[] coilValues= new boolean[] {true,false,true};
			WriteCoilsRequest writeRequest = new WriteCoilsRequest(slaveId,startOffset,coilValues);
			WriteCoilsResponse writeResponse = (WriteCoilsResponse)master.send(writeRequest);
			if(writeResponse.isException()) {
				System.out.println("Hata Yanıtı:"+ writeResponse.getExceptionMessage());
				
			}
			else {
				System.out.println("Bobine Yazma Başarılı");
		
			}
			
			short[] registerValues =new short[] {89,345,675};
			
			
			WriteRegistersRequest writeRegistersRequest=new WriteRegistersRequest(slaveId,startOffset,registerValues);
			WriteRegistersResponse writeRegistersResponse= (WriteRegistersResponse)master.send(writeRegistersRequest);
		
			if(writeRegistersResponse.isException()) {
				System.out.println("Yazılan Kayıtlar Hatalı"+ writeRegistersResponse.getExceptionMessage());
			}
			else {
				System.out.println("Yazılan Kayıtlar Başarılı");
			}

		}catch(ModbusInitException e) {
			e.printStackTrace();	
			}
		catch(Exception e) {
			e.printStackTrace();	
		}
		finally{
			master.destroy();
			
		}
				  	
	}
}