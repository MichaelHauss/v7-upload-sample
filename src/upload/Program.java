package upload;

import java.io.InputStream;
import java.io.FileInputStream;
import upload.Uuid;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;


public class Program {
	
	public static void main(String[] args) {

		try {    
			
			// Parse the connection string and create a blob client to interact with Blob storage.
			// Note that this code relies on uploading to an extant blob named "video.mp4" in
			// a container named "videos".
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(args[1]);
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
			CloudBlobContainer container = blobClient.getContainerReference("videos");
			CloudBlockBlob blob = container.getBlockBlobReference(args[0]);
			
			BlobRequestOptions options = new BlobRequestOptions();
			options.setDisableContentMD5Validation(true);

			// Upload blocks continuously
			System.out.println("Uploading blocks with legacy Java SDK...");
			Uuid uuid = new Uuid();
            while(true){
            	InputStream inputstream = new FileInputStream("./block");
            	blob.uploadBlock(uuid.randomId(), inputstream, 104857600L, null, options, null);
            	
            }
		} 
		catch (StorageException ex)
		{
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
		}
		catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}

	}

}