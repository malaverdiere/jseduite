/**
 * This file is part of jSeduite::FileUploader
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::FileUploader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::FileUploader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::FileUploader; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 *
 **/
package fr.unice.i3s.modalis.jseduite.upload.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class FileUploader {

    private final static String path = "../applications/j2ee-modules/FileUploader/resources/";
    private final static String context = "/jSeduite/FileUploader/resources/";

    @WebMethod(operationName = "uploadNewFile")
    public void uploadNewFile(@WebParam(name = "name") String name,
                             @WebParam(name = "content") byte[] content,@WebParam(name = "folder") String folder)
            throws FileUploaderException {

        //on test si le dossier existe
        File folderFile = new File(path+folder);
        if(folderFile.isFile()){
            throw new FileUploaderException("can't create folder");
        }
        if(!folderFile.exists()){
            folderFile.mkdir();
        }
        String fileName = name;
        File file = new File(path+folder+fileName);
        int i = 0;
        while(file.exists()) {
            i++;
            fileName = name.substring(0, name.lastIndexOf('.'))+"("+i+")"+name.substring(name.lastIndexOf('.'));
            file = new File(path+folder+fileName);
        }

		try {
            FileOutputStream f = new FileOutputStream(file);
            for (int j = 0; j < content.length; j++) {
                f.write(content[j]);
            }
            f.close();
        }
        catch (IOException e){
			throw new FileUploaderException(e.getMessage());
		}
    }

    @WebMethod(operationName = "deleteFile")
    public void deleteFile(@WebParam(name = "name") String name)
            throws FileUploaderException {

        File file = new File(path+name);

        if(!file.exists()) {
            throw new FileUploaderException("The file "+name+" does not exist.");
        }

        file.delete();
    }

    @WebMethod(operationName = "getFolderFiles")
    public String[] getFolderFiles(@WebParam(name = "folder") String folder) {
        File file = new File(path + "/" + folder);

        return file.list();
    }

    @WebMethod(operationName = "getAllFiles")
    public String[] getAllFiles() {
        File file = new File(path);

        return file.list();
    }

    @WebMethod(operationName = "getURL")
    public String getURL(@WebParam(name = "name") String name)
            throws FileUploaderException {

        File file = new File(path+name);
        if(file.exists()) {
            return context + name;
        }

        return null;
    }
}
