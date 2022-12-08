package com.emergentes.utiles;

import com.emergentes.modelo.DetalleVenta;
import com.emergentes.modelo.Venta;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class Comprobante {

    private static final String OUT_FILE_URL = "C:app-web-proyecto\\src\\main\\webapp\\comprobantes\\";
    private static final String IN_IMAGE_FILENAME = "C:app-web-proyecto\\src\\main\\webapp\\img\\ferreteria1.png";

    public static void generarPDF(Venta venta) {
        try {
            String[] header1 = {"SEÑOR (ES)", ":" + venta.getCliente().getNombres().concat(" " + venta.getCliente().getApellidos()), "FECHA", ":" + venta.getFechaVenta()};
            String[] header2 = {"NIT", ":" + venta.getCliente().getCi(), "", ""};
            String[] header3 = {"DIRECCION", ":" + venta.getCliente().getDireccion(), "", ""};
            String[] header4 = {"REFERENCIA", ":" + venta.getCliente().getEmail(), "", ""};

            Double[] mount = {0.00, venta.getSubTotal(), venta.getIva(), venta.getMontoVenta()};
            String desMount = "Bolivianos";
            String periodo = venta.getFechaVenta().toString();

            String outFilename = OUT_FILE_URL.concat(venta.getNumeroVenta().concat(".pdf"));
            String i_num_id_doc = venta.getNumeroVenta();

            int cantidad = venta.getDetalleVenta().size();
            createPDF(outFilename, IN_IMAGE_FILENAME, header1, header2, header3, header4, i_num_id_doc, venta.getDetalleVenta(), cantidad, mount, desMount, periodo);

        } catch (Exception e) {
            System.err.println("Error1:" + e);
        }
    }

    private static void createPDF(String outFilename, String inImageFilename, String[] header1, String[] header2, String[] header3, String[] header4, String i_num_id_doc, List<DetalleVenta> detalleVenta, int cantidad, Double[] mount, String desMount, String periodo) {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        Paragraph space = new Paragraph(" ");
        String[] infoFooter = {desMount, periodo};
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outFilename));
            document.open();
            document.add(addInfoHome(inImageFilename, i_num_id_doc));
            document.add(space);
            document.add(createHeader(header1, header2, header3, header4));
            document.add(space);
            document.add(createHeaderTable());
            document.add(createBodyTable(detalleVenta, cantidad));
            document.add(createCalculate(mount));
            document.add(space);
            document.add(addInfoFooter(infoFooter));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.err.println("Error2:" + e);
        }
    }

    public static PdfPTable addInfoHome(String inImageFilename, String i_num_id_doc) {
        PdfPTable pdfPTable = new PdfPTable(2);
        try {
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setWidths(new float[]{18.0f, 6.0f});
            pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            pdfPTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            pdfPTable.addCell(addInfoHome1(inImageFilename));
            pdfPTable.addCell(addInfoHome2(i_num_id_doc));
        } catch (DocumentException e) {
            System.err.println("Error3:" + e);
        }
        return pdfPTable;
    }

    public static PdfPTable addInfoHome1(String inImageFilename) {
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(1);
        try {
            pdfPTable.setWidthPercentage(35);
            pdfPTable.setWidths(new float[]{10.0f});
            pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            pdfPTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell cell = new PdfPCell(addImageToPDF(inImageFilename));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPadding(1);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase("EMPRESA DE VENTAS", font1));
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase("Calle Los Alamos - El Alto", font2));
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase("Telf.:1234-345, Fax: 435-223", font2));
            pdfPTable.addCell(cell);
        } catch (DocumentException e) {
            System.err.println("Error4:" + e);
        }
        return pdfPTable;
    }

    public static Image addImageToPDF(String inImageFilename) {
        Image image = null;
        try {
            image = Image.getInstance(inImageFilename);
            image.scaleAbsoluteWidth(150f);
            image.scaleAbsoluteHeight(30f);
            image.getLeft();
        } catch (BadElementException | IOException e) {
            System.err.println("ErrorImage:" + e);
        }
        return image;
    }

    public static PdfPTable addInfoHome2(String i_num_id_doc) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(1);
        try {
            pdfPTable.setWidthPercentage(35);
            pdfPTable.setWidths(new float[]{6.0f});
            pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cell = new PdfPCell();
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setBorder(Rectangle.BOX);
            cell.setBorderWidth(2);
            cell.setPaddingTop(7);

            cell.setPhrase(new Phrase("N.I.T.: 20229999999", font));
            cell.disableBorderSide(Rectangle.BOTTOM);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase("COMPROBANTE", font));
            cell.disableBorderSide(Rectangle.TOP);
            cell.disableBorderSide(Rectangle.BOTTOM);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase("N°".concat(i_num_id_doc), font));
            cell.setBorder(Rectangle.BOX);
            cell.disableBorderSide(Rectangle.TOP);
            pdfPTable.addCell(cell);
        } catch (DocumentException e) {
            System.err.println("Error5:" + e);
        }
        return pdfPTable;
    }

    public static PdfPTable createHeader(String[] header1, String[] header2, String[] header3, String[] header4) {
        Font font = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(4);
        try {
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setWidths(new float[]{3.0f, 16.0f, 2.0f, 3.0f});

            PdfPCell cell1 = new PdfPCell();
            for (String header : header1) {
                cell1.setPhrase(new Phrase(header, font));
                cell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell1.setBorder(0);
                pdfPTable.addCell(cell1);
            }
            PdfPCell cell2 = new PdfPCell();
            for (String header : header2) {
                cell2.setPhrase(new Phrase(header, font));
                cell2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell2.setBorder(0);
                pdfPTable.addCell(cell2);
            }
            PdfPCell cell3 = new PdfPCell();
            for (String header : header3) {
                cell3.setPhrase(new Phrase(header, font));
                cell3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell3.setBorder(0);
                pdfPTable.addCell(cell3);
            }
            PdfPCell cell4 = new PdfPCell();
            for (String header : header4) {
                cell4.setPhrase(new Phrase(header, font));
                cell4.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell4.setBorder(0);
                pdfPTable.addCell(cell4);
            }
        } catch (DocumentException e) {
            System.err.println("Error6:" + e);
        }
        return pdfPTable;
    }

    private static PdfPTable createHeaderTable() {
        Font font = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(5);
        try {
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setWidths(new float[]{1.0f, 12.0f, 2.0f, 4.0f, 4.0f});

            PdfPCell cell = new PdfPCell();
            String[] header = {"#", "Descripción", "Cantidad", "Precio", "Importe"};
            for (String head : header) {
                cell.setPhrase(new Phrase(head, font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setPaddingBottom(4);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);

                pdfPTable.addCell(cell);
            }
        } catch (DocumentException e) {
            System.err.println("Error7:" + e);
        }
        return pdfPTable;
    }

    private static PdfPTable createBodyTable(List<DetalleVenta> detalleVentas, int cantidad) {
        Font font = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(5);
        try {
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setWidths(new float[]{1.0f, 12.0f, 2.0f, 4.0f, 4.0f});

            PdfPCell cell = new PdfPCell();

            int j = 0;
            int contador = 1;
            for (DetalleVenta dv : detalleVentas) {
                String[] headerValue = {String.valueOf(contador), dv.getProducto().getNombre(), String.valueOf(dv.getCantidad()), dv.getPrecioVentaf(), dv.getPrecioTotalf()};
                for (int i = 0; i < headerValue.length; i++) {

                    switch (i) {
                        case 1:
                            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                            break;
                        case 3:
                            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        case 4:
                            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            break;
                        default:
                            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                            break;
                    }
                    cell.setPhrase(new Phrase(headerValue[i], font));

                    if (j + 1 == cantidad) {
                        cell.setPaddingBottom(120);
                        cell.setBorder(14);
                    } else {
                        cell.setBorder(12);
                    }
                    pdfPTable.addCell(cell);
                }
                contador++;
                j++;
            }
        } catch (DocumentException e) {
            System.err.println("Error8:" + e);
        }
        return pdfPTable;
    }

    public static PdfPTable createCalculate(Double[] mount) {
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(3);
        DecimalFormat dFormat = new DecimalFormat("0.00");
        try {
            pdfPTable.setWidthPercentage(35);
            pdfPTable.setWidths(new float[]{6.0f, 1.5f, 4.5f});
            pdfPTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

            String[] description = {"Descuento", "Sub Total", "Total IVA", "Importe Total"};

            PdfPCell[] cell = new PdfPCell[3];
            for (int i = 0; i < description.length; i++) {
                cell[0] = new PdfPCell();
                cell[0].setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell[0].setPhrase(new Phrase(description[i], font1));
                cell[0].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell[0].setBorder(Rectangle.BOX);
                pdfPTable.addCell(cell[0]);

                cell[1] = new PdfPCell();
                cell[1].setBackgroundColor(BaseColor.WHITE);
                cell[1].setPhrase(new Phrase("Bs.", font2));
                cell[1].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                cell[1].setBorder(3);
                pdfPTable.addCell(cell[1]);

                cell[2] = new PdfPCell();
                cell[2].setBackgroundColor(BaseColor.WHITE);
                cell[2].setPhrase(new Phrase(String.valueOf(dFormat.format(mount[i])), font2));
                cell[2].setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                cell[2].setBorder(11);
                pdfPTable.addCell(cell[2]);

            }
        } catch (DocumentException e) {
            System.err.println("Error9:" + e);
        }
        return pdfPTable;
    }

    private static PdfPTable addInfoFooter(String[] infoFooter) {
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, BaseColor.BLACK);
        PdfPTable pdfPTable = new PdfPTable(3);
        try {
            pdfPTable.setWidthPercentage(90);
            pdfPTable.setWidths(new float[]{2f, 0.5f, 22.0f});
            pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            String[] descripcion = {"SON", "PERIODO"};
            PdfPCell[] cell = new PdfPCell[3];

            for (int i = 0; i < descripcion.length; i++) {
                cell[0] = new PdfPCell();
                cell[0].setPhrase(new Phrase(descripcion[i], font1));
                cell[0].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell[0].setBorder(0);
                pdfPTable.addCell(cell[0]);

                cell[1] = new PdfPCell();
                cell[1].setPhrase(new Phrase(":", font1));
                cell[1].setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell[1].setBorder(0);
                pdfPTable.addCell(cell[1]);

                cell[2] = new PdfPCell();
                cell[2].setPhrase(new Phrase(infoFooter[i], font2));
                cell[2].setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                cell[2].setBorder(0);
                pdfPTable.addCell(cell[2]);
            }
        } catch (DocumentException e) {
            System.err.println("Error10:" + e);
        }
        return pdfPTable;
    }

//    public static void main(String[] args) {
//        Venta venta = new Venta();
//        venta.setId(1);
//        venta.setCliente(new Cliente("0000002", "JUAN", "SOTO MAYOR", "LOS PORTALES", "juan@gmail.com"));
//        venta.setMontoVenta(100.00);
//        venta.setNumeroVenta("0000000001");
//        venta.setFechaVenta(new Date(new java.util.Date().getTime()));
//        venta.setUsuario(new Usuario(1));
//
//        List<DetalleVenta> detalleVentas = new ArrayList<>();
//        DetalleVenta detalleVenta = new DetalleVenta();
//        detalleVenta.setId(1);
//        detalleVenta.setPrecioVenta(10.00);
//        detalleVenta.setCantidad(10);
//        detalleVenta.setProducto(new Producto(1, "Teclado Logitech 234", "LOGITECH"));
//        detalleVentas.add(detalleVenta);
//        venta.setDetalleVenta(detalleVentas);
//        generarPDF(venta);
//    }
}
