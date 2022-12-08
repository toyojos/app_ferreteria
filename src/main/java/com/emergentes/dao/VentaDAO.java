
package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import java.util.List;

public interface VentaDAO {
    public int insert(Venta venta) throws Exception;
    public void update(Venta venta) throws Exception;
    public void delete(int id_venta) throws Exception;
    public Venta getById(int id_venta) throws Exception;
    public String getLastIdNum() throws Exception;
    public List<Venta> getAll() throws Exception;
}
