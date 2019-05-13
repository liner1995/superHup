package cn.hups.common.utils;

import cn.hups.common.exception.GlobalException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 本系统中的工具类
 */
public class FileUtils {

    // 产品信息相关文件目录
    public static final String PRODUCT_BOM_FILEPATH = "files/product/bomfile"; // BOM文件目录
    public static final String PRODUCT_PCB_FILEPATH = "files/product/pcbfile"; // PCB文件目录
    public static final String PRODUCT_CRAFT_FILEPATH = "files/product/craftfile"; // 工艺文件目录
    // 采购报价文件目录
    public static final String PURCHASE_FILEPATH = "files/purchase"; // 采购报价目录

    // 产品信息相关文件key
    public static final String PRODUCT_NAME ="FILE_NAME"; // 文件名称
    public static final String PRODUCT_FULL_NAME ="FILE_FULL_NAME"; // 文件全路径
    public static final String PRODUCT_BOM_NAME = "BOM_FILE_NAME"; // BOM文件名称
    public static final String PRODUCT_BOM_FULL_NAME = "BOM_FILE_FULL_NAME"; // BOM文件全路径
    public static final String PRODUCT_PCB_NAME = "PCB_FILE_NAME"; // PCB文件名称
    public static final String PRODUCT_PCB_FULL_NAME = "PCB_FILE_FULL_NAME"; // PCB文件全路径
    public static final String PRODUCT_CRAFT_NAME = "CRAFT_FILE_NAME"; // 工艺文件名称
    public static final String PRODUCT_CRAFT_FULL_NAME = "CRAFT_FILE_FULL_NAME"; // 工艺文件全路径
    // 采购信息相关文件Key
    public static final String PURCHASE_NAME = "PURCHASE_FILE_NAME";
    public static final String PURCHASE_FULL_NAME = "PURCHASE_FILE_FULL_NAME";

    // 保存文件错误信息
    public static final String SAVE_FILE_NULL = "文件信息不完整，保存失败!";
    public static final String CREATE_DIR_ERROR = "创建文件夹失败!";
    public static final String SAVE_FILE_TO_DIR_ERROR = "保存文件到文件夹失败!";

    /**
     * 将MultipartFile转换为java.io.File
     * @param mfile
     * @return
     */
    public File multipartToFile(MultipartFile mfile) {
        File f = null;
        try {
            if (mfile != null) {
                InputStream ins = mfile.getInputStream();
                f = new File(mfile.getOriginalFilename());

                OutputStream os = new FileOutputStream(f);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                ins.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    /**
     * 根据参数获取项目静态文件夹下的具体路径
     * @param path
     * @return
     */
    public static String getModulePath(String path){
        return getClassPath("") + path;
    }

    /**
     * 获取静态文件夹下的真实路径
     * @param path
     * @return
     */
    public static String getClassPath(String path){
        if (org.springframework.util.StringUtils.isEmpty(path)) {
            path = "";
        }
        try {
            return Objects.requireNonNull(Objects.requireNonNull(ResourceUtils.getURL("classpath:").getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存产品信息文件到服务器
     * @param file1 BOM文件
     * @param file2 PCB文件
     * @param file3 工艺文件
     * @throws GlobalException
     */
    public Map<String, String> saveProductFiles(MultipartFile file1, MultipartFile file2, MultipartFile file3) throws GlobalException {
        Map<String, String> resultMap = new HashMap<>();

        Map<String, String> saveBomMap = saveFileByPath(file1, PRODUCT_BOM_FILEPATH);
        Map<String, String> savePcbMap = saveFileByPath(file2, PRODUCT_PCB_FILEPATH);
        Map<String, String> saveCractMap = saveFileByPath(file3, PRODUCT_CRAFT_FILEPATH);


        resultMap.put(PRODUCT_BOM_NAME, saveBomMap.get(PRODUCT_NAME));
        resultMap.put(PRODUCT_BOM_FULL_NAME, saveBomMap.get(PRODUCT_FULL_NAME));
        resultMap.put(PRODUCT_PCB_NAME, savePcbMap.get(PRODUCT_NAME));
        resultMap.put(PRODUCT_PCB_FULL_NAME, savePcbMap.get(PRODUCT_FULL_NAME));
        resultMap.put(PRODUCT_CRAFT_NAME, saveCractMap.get(PRODUCT_NAME));
        resultMap.put(PRODUCT_CRAFT_FULL_NAME, saveCractMap.get(PRODUCT_FULL_NAME));

        return resultMap;
    }

    /**
     * 保存采购报价文件
     * @param purfile
     * @return
     * @throws GlobalException
     */
    public Map<String, Object> savePurchaseFile(MultipartFile purfile) throws GlobalException {
        Map<String, Object> resultMap = new HashMap<>();

        Map<String, String> saveBomMap = saveFileByPath(purfile, PURCHASE_FILEPATH);

        resultMap.put(PURCHASE_NAME, saveBomMap.get(PRODUCT_NAME));
        resultMap.put(PURCHASE_FULL_NAME, saveBomMap.get(PRODUCT_FULL_NAME));

        return resultMap;
    }

    /**
     * 根据文件及目录保存文件信息
     * @param mutfile
     * @param savePath
     * @return
     * @throws GlobalException
     */
    private Map<String, String> saveFileByPath(MultipartFile mutfile, String savePath) throws GlobalException {
        Map<String, String> saveMap = new HashMap<>();
        String fileFullName = "";

        try {
            if (mutfile != null) {
                InputStream inputStream = mutfile.getInputStream();
                String dir = getModulePath(savePath);
                File file = new File(dir);
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        throw new GlobalException(CREATE_DIR_ERROR);
                    }
                }
//                String mutFileName = mutfile.getResource().getFilename();
                String mutFileName = mutfile.getOriginalFilename();
                String fileName = renameFile(mutFileName);
                saveMap.put(PRODUCT_NAME, fileName);
                file = new File(dir, fileName);
                fileFullName = dir + "/" + fileName;
                OutputStream outputStream = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            throw new GlobalException(SAVE_FILE_TO_DIR_ERROR);
        }

        saveMap.put(PRODUCT_FULL_NAME, fileFullName);

        return saveMap;
    }

    /**
     * 重命文件名
     * @param fileName
     * @return
     */
    private String renameFile(String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            String[] fileNameSplit = fileName.split("\\.");
            if (fileNameSplit.length > 0) {
                String lastName = fileNameSplit[fileNameSplit.length - 1];
                String needReplaceLast = "." + lastName;
                String firstName = fileName.replaceAll(needReplaceLast, "");

                DateUtils dateUtils = new DateUtils();
                RanDomUtils ranDomUtil = new RanDomUtils();
                firstName = firstName + dateUtils.getKnowTsChar() + ranDomUtil.generateDigitalString(6);

                fileName = firstName + "." + lastName;
            }
        }
        return fileName;
    }
}
