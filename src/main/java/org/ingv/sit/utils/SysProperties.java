/**
 *
 *
 * Andrea Bono
 * I.N.G.V. Isituto Nazionale di Geofisica e Vulcanologia
 * C.N.T. Centro Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 * andrea.bono@ingv.it
 *
 */

package org.ingv.sit.utils;

public class SysProperties {
    public String file_Separator;
    public String java_class_path;
    public String java_home;
    public String java_version;
    public String line_separator;
    public String os_architecture;
    public String os_name;
    public String os_version;
    public String path_separator;
    public String user_dir;
    public String user_home;
    public String user_name;
    public String temporary_dir;
    public String jt_temporary_dir;
    //public String seisbook_sac_temporary_dir;
    
    public SysProperties(){
        this.Read_System_Properties();
    }

    public void Read_System_Properties(){
        this.file_Separator = System.getProperty("file.separator");
        this.java_class_path= System.getProperty("java.class.path");
        this.java_home= System.getProperty("java.home");
        this.java_version= System.getProperty("java.version");
        this.line_separator= System.getProperty("line.separator");
        this.os_architecture= System.getProperty("os.arch");
        this.os_name= System.getProperty("os.name");
        this.os_version= System.getProperty("os.version");
        this.path_separator= System.getProperty("path.separator");
        this.user_dir= System.getProperty("user.dir");
        this.temporary_dir=System.getProperty("java.io.tmpdir");
        this.user_home= System.getProperty("user.home");
        this.user_name= System.getProperty("user.name");
        this.jt_temporary_dir=this.temporary_dir+this.file_Separator+"jet";
        //this.jt_temporary_dir=this.temporary_dir + "jet";
    }
}
