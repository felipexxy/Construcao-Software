package com.applyandgrowth.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	
	public static boolean fazerUploadImagem(MultipartFile imagem){

        boolean sucessoUpload = false;

        if(!imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try {

                String pastaUploadImagem = "src//main//resources//static//img//img-uploads";
                File dir = new File(pastaUploadImagem);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();

                sucessoUpload = true;

            } catch (Exception e) {
               System.out.println("Você falhou em carregar o arquivo : " + nomeArquivo + " =>" + e.getMessage());
            }
            
        }else{
            System.out.println("Falha ao carregar o arquivo (Vázio)");

        }

        return sucessoUpload;

    }
}
