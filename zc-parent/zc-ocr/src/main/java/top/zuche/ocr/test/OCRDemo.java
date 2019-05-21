package top.zuche.ocr.test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @author lzx
 * @date 2019/5/19 下午7:26
 */
public class OCRDemo {

    public static void main(String[] args) {
//        System.setProperty("jna.library.path", "lib");

        ITesseract instance = new Tesseract();
//        instance.setLanguage("chi_sim");

        File img = new File("/Users/meepai/Downloads/number.jpeg");
        long startTime = System.currentTimeMillis();
        try {
            String ocrResult = instance.doOCR(img);
            System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        } catch (TesseractException e) {
//            e.printStackTrace();
        }
    }
}
