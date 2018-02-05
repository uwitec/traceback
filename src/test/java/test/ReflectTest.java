package test; 
 
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.beans.factory.annotation.Autowired;

import com.tmsps.traceback.base.service.BaseService;
import com.tmsps.traceback.model.t_shop; 
 
public class ReflectTest { 

	@Autowired
	BaseService bs;
	
    public static void main(String[] args) throws Exception { 
        String packageName = "com.tmsps.qihang.model"; 
        
        List<String> classNames = getClassName(packageName); 
        System.out.println(classNames);
        
        System.out.println(classNames.size());
            for (String className : classNames) { 
                System.out.println("包名    "+className);  
                
                
                Field[] ff= classNames.getClass().getDeclaredFields();
                for(int i=0;i<ff.length;i++){
                	Field fields =ff[i];
                	System.out.println("名称为："+fields.getName());
                	Class fieldType =fields.getType();
                	
                }
            } 
            
            
            
            
            
            t_shop t = new t_shop();
            Class exampleC = t.getClass();
           
            Field[] declaredFeields =exampleC.getDeclaredFields();
            for(int i=0;i<declaredFeields.length;i++){
            	Field field =declaredFeields[i];
            	System.out.println("名称为   "+field.getName());
            	Class fieldType =field.getType();
            	
            }
            
       
            
    } 
 
    /**
     * 获取某包下（包括该包的所有子包）所有类
     * @param packageName 包名
     * @return 类的完整名称
     */ 
    public static List<String> getClassName(String packageName) { 
        return getClassName(packageName, true); 
    } 
 
    /**
     * 获取某包下所有类
     * @param packageName 包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */ 
    public static List<String> getClassName(String packageName, boolean childPackage) { 
        List<String> fileNames = null; 
        ClassLoader loader = Thread.currentThread().getContextClassLoader(); 
        String packagePath = packageName.replace(".", "/"); 
        URL url = loader.getResource(packagePath); 
        if (url != null) { 
            String type = url.getProtocol(); 
            if (type.equals("file")) { 
                fileNames = getClassNameByFile(url.getPath(), null, childPackage); 
            } else if (type.equals("jar")) { 
                fileNames = getClassNameByJar(url.getPath(), childPackage); 
            } 
        } else { 
            fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage); 
        } 
        return fileNames; 
    } 
 
    /**
     * 从项目文件获取某包下所有类
     * @param filePath 文件路径
     * @param className 类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */ 
    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) { 
        List<String> myClassName = new ArrayList<String>(); 
        File file = new File(filePath); 
        File[] childFiles = file.listFiles(); 
        for (File childFile : childFiles) { 
            if (childFile.isDirectory()) { 
                if (childPackage) { 
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage)); 
                } 
            } else { 
                String childFilePath = childFile.getPath(); 
                if (childFilePath.endsWith(".class")) { 
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf(".")); 
                    childFilePath = childFilePath.replace("\\", "."); 
                    myClassName.add(childFilePath); 
                } 
            } 
        } 
 
        return myClassName; 
    } 
 
    /**
     * 从jar获取某包下所有类
     * @param jarPath jar文件路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */ 
    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) { 
        List<String> myClassName = new ArrayList<String>(); 
        String[] jarInfo = jarPath.split("!"); 
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/")); 
        String packagePath = jarInfo[1].substring(1); 
        try { 
            JarFile jarFile = new JarFile(jarFilePath); 
            Enumeration<JarEntry> entrys = jarFile.entries(); 
            while (entrys.hasMoreElements()) { 
                JarEntry jarEntry = entrys.nextElement(); 
                String entryName = jarEntry.getName(); 
                if (entryName.endsWith(".class")) { 
                    if (childPackage) { 
                        if (entryName.startsWith(packagePath)) { 
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf(".")); 
                            myClassName.add(entryName); 
                        } 
                    } else { 
                        int index = entryName.lastIndexOf("/"); 
                        String myPackagePath; 
                        if (index != -1) { 
                            myPackagePath = entryName.substring(0, index); 
                        } else { 
                            myPackagePath = entryName; 
                        } 
                        if (myPackagePath.equals(packagePath)) { 
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf(".")); 
                            myClassName.add(entryName); 
                        } 
                    } 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return myClassName; 
    } 
 
    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     * @param urls URL集合
     * @param packagePath 包路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */ 
    private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) { 
        List<String> myClassName = new ArrayList<String>(); 
        if (urls != null) { 
            for (int i = 0; i < urls.length; i++) { 
                URL url = urls[i]; 
                String urlPath = url.getPath(); 
               
                if (urlPath.endsWith("classes/")) { 
                    continue; 
                } 
                String jarPath = urlPath + "!/" + packagePath; 
                myClassName.addAll(getClassNameByJar(jarPath, childPackage)); 
            } 
        } 
        return myClassName; 
    } 
}