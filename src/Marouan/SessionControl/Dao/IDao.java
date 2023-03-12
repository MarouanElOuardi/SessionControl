package Marouan.SessionControl.Dao;

import java.io.IOException;

public interface IDao<T,ID> {
    T trouverParId(ID id);
}
