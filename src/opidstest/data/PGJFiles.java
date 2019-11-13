package opidstest.data;

import opidstest.data.DataSet;
import opidstest.data.DataSetProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PGJFiles extends DataSet implements DataSetProvider
{
    private void parseFile(String fileName) throws ParseException
    {
        try
        {
            // create a Buffered Reader object instance with a FileReader
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // read the first line from the text file
            String fileRead = br.readLine();
            fileRead = br.readLine();
            List<List<? extends  Object>> thisData = new ArrayList<>();
            List<Integer> thisLabels = new ArrayList<>();
            Integer i = 1;
            Boolean failed = false;
            
            // loop until all lines are read
            while (fileRead != null)
            {
                // use string.split to load a string array with the values from each line of
                // the file, using a comma as the delimiter
                String[] tokenize = fileRead.split(";",-1);

                List<Object> thisRow = new ArrayList<>();
//                thisRow.add(tokenize[0]); // ao_hechos
//
//                thisRow.add(tokenize[1]); // mes_hechos                            
//                Double month;
//                switch(tokenize[1]) {
//                    case "Enero":
//                        month = 1d;
//                        break;
//                    case "Febrero":
//                        month = 2d;
//                        break;
//                    case "Marzo":
//                        month = 3d;
//                        break;
//                    case "Abril":
//                        month = 4d;
//                        break;
//                    case "Mayo":
//                        month = 5d;
//                        break;
//                    case "Junio":
//                        month = 6d;
//                        break;
//                    case "Julio":
//                        month = 7d;
//                        break;
//                    case "Agosto":
//                        month = 8d;
//                        break;
//                    case "Septiembre":
//                        month = 9d;
//                        break;
//                    case "Octubre":
//                        month = 10d;
//                        break;
//                    case "Npviembre":
//                        month = 11d;
//                        break;
//                    case "Diciembre":
//                        month = 12d;
//                        break;    
//                    default:
//                        month = 0d;
//                        break;                                                
//                }
//                if (!tokenize[2].equalsIgnoreCase("NA")) {
//                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tokenize[2]);
//                    Double month = (date.getMonth() + 1d) + (date.getDate() / 32d);
//                    thisRow.add(month); // mes_hechos(número)
//                }
//                else thisRow.add(0d); 
                
                //thisRow.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokenize[2])); // fecha_hechos
//                thisRow.add(tokenize[2]); // fecha_hechos
//                thisRow.add(tokenize[3]); // delito
//                thisRow.add(tokenize[4]); // categoria_delito
                Double categoriaDelito;
                switch(tokenize[4]) {
                    case "DELITO DE BAJO IMPACTO":
                        categoriaDelito = 1d;
                        break;
                    case "HECHO NO DELICTIVO":
                        categoriaDelito = 2d;
                        break;
                    case "ROBO A TRANSEUNTE EN VÍA PÚBLICA CON Y SIN VIOLENCIA":
                        categoriaDelito = 3d;
                        break;
                    case "ROBO DE VEHÍCULO CON Y SIN VIOLENCIA":
                        categoriaDelito = 4d;
                        break;
                    case "ROBO A NEGOCIO CON VIOLENCIA":
                        categoriaDelito = 5d;
                        break;
                    case "ROBO A PASAJERO A BORDO DEL METRO CON Y SIN VIOLENCIA":
                        categoriaDelito = 6d;
                        break;
                    case "ROBO A REPARTIDOR CON Y SIN VIOLENCIA":
                        categoriaDelito = 7d;
                        break;
                    case "LESIONES DOLOSAS POR DISPARO DE ARMA DE FUEGO":
                        categoriaDelito = 8d;
                        break;
                    case "HOMICIDIO DOLOSO":
                        categoriaDelito = 9d;
                        break;
                    case "VIOLACIÓN":
                        categoriaDelito = 10d;
                        break;
                    case "ROBO A PASAJERO A BORDO DE MICROBUS CON Y SIN VIOLENCIA":
                        categoriaDelito = 11d;
                        break;
                    case "ROBO A CASA HABITACIÓN CON VIOLENCIA":
                        categoriaDelito = 12d;
                        break;
                    case "ROBO A CUENTAHABIENTE SALIENDO DEL CAJERO CON VIOLENCIA":
                        categoriaDelito = 13d;
                        break;                        
                    case "ROBO A PASAJERO A BORDO DE TAXI CON VIOLENCIA":
                        categoriaDelito = 14d;
                        break;                        
                    case "ROBO A TRANSPORTISTA CON Y SIN VIOLENCIA":
                        categoriaDelito = 15d;
                        break;                        
                    case "SECUESTRO":
                        categoriaDelito = 16d;
                        break;                        
                    default:
                        categoriaDelito = 17d;
                        break;                        
                }
                thisRow.add(categoriaDelito); // categoria_delito(numero)
//                thisRow.add(tokenize[5]); // fiscalia
//                thisRow.add(tokenize[6]); // agencia
//                thisRow.add(tokenize[7]); // unidad_investigacion
//                thisRow.add(tokenize[8]); // alcaldia_hechos
//                thisRow.add(tokenize[9]); // colonia_hechosao_inicio
//                thisRow.add(Integer.parseInt(tokenize[10])); // ao_inicio   
//                thisRow.add(tokenize[11]); // mes_inicio  
//                thisRow.add(tokenize[12]); // fecha_inicio                
//                thisRow.add(tokenize[13]); // calle_hechos
//                thisRow.add(tokenize[14]); // calle_hechos2
                if (!"NA".equals(tokenize[15]) && !"".equals(tokenize[15])) {
                    try {
                    thisRow.add(Double.parseDouble(tokenize[15])); // longitud
                    }
                    catch(Exception e) {
                        failed = true;
                    }
                }
                else failed = true;
                
                if (!"NA".equals(tokenize[16]) && !"".equals(tokenize[16])) {
                    try {
                    thisRow.add(Double.parseDouble(tokenize[16])); // latitud
                    }
                    catch(Exception e) {
                        failed = true;
                    }
                }
                else failed = true;
//                thisRow.add(tokenize[17]); // geopoint
                
                if (!failed) {
                    thisData.add(thisRow);
                    
                }
                else failed = false;

                // read next line before looping
                // if end of file reached
                fileRead = br.readLine();
            }

            setData(thisData);
            setLabels(thisLabels);

            // close file stream
            br.close();
        }

        // handle exceptions
        catch (FileNotFoundException fnfe)
        {
            System.out.println("file not found");
        }

        catch (IOException ioe)
        {
            ioe.printStackTrace();
        } 
    }

    @Override
    public void loadDataSet() {
        try {
              parseFile("dbs/carpetas-de-investigacion-pgj-de-la-ciudad-de-mexico.csv");
//            parseFile("dbs/test.csv");
        } catch (ParseException ex) {
            Logger.getLogger(PGJFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNSamples(getData().size());
        setDSamples(getData().get(0).size());
        //setK(2);
    }

    @Override
    public DataSet getDataSet() {
        return this;
    }
}