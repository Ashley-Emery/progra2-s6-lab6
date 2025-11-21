/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s6.lab6;

/**
 *
 * @author ashley
 */

import java.io.File;
import java.awt.Component;

public class MiCmd {
    
    private File currentDir;
    
    public MiCmd(){
        
        currentDir = new File("C:\\");
        
        if(!currentDir.exists()){
            currentDir = new File(System.getProperty("user.home"));
        }
    }
    
    public String getPrompt(){
        return "\n" + currentDir.getAbsolutePath() + ">";
    }
    
    public String procesarComando(String linea, Component parent){
        
        StringBuilder sb = new StringBuilder();
        
        linea = linea.trim();
        
        if(linea.isEmpty()){
            return "";
        }
        
        String[] partes = linea.split("\\s+");
        
        String comando = partes[0];
        String argumentos = "";
        
        if(linea.length() > comando.length()){
            argumentos = linea.substring(comando.length()).trim();
        }
        
        switch (comando){
            case "Mkdir":
                sb.append(cmdMkdir(argumentos));
                break;
            case "Mfile":
                sb.append(cmdMfile(argumentos));
                break;
            case "Rm":
                sb.append(cmdRm(argumentos));
                break;
            case "Cd":
                sb.append(cmdCd(argumentos));
                break;
            case "Dir":
                sb.append(cmdDir(argumentos));
                break;
            case "Date":
                sb.append(cmdDate());
                break;
            case "Time":
                sb.append(cmdTime());
                break;
            case "wr":
                sb.append(cmdWrite(argumentos, parent));
                break;
            case "rd":
                sb.append(cmdRead(argumentos));
                break;
            default:
                sb.append("Comando no reconocido.\n");
                break;
        }
        
        return sb.toString();
        
    }
    
    public String cmdMkdir(String nombre){
        
        if(nombre.isEmpty()){
            return "Uso: Mkdir <nombre>\n";
        }
        
        File nueva = new File(currentDir, nombre);
        
        if (nueva.exists()) {
            return "La carpeta ya existe.\n";
        }
        
        if (nueva.mkdir()) {
            return "Carpeta creada: " + nueva.getName() + "\n";
        } else {
            return "No se pudo crear la carpeta.\n";
        }
        
    }
    
    
    
}
