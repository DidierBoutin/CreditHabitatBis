package crud.repository;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

	
	public Connection connect = ConnectionBase.getInstance();

	 

    /**
    * Permet de récupérer un objet via son nom
    */
//    public abstract T find(String name);


    /**
    * Permet de récupérer tous les objets du même nom
    */
    public abstract List<T> findAll();

    
    /**
    * Permet de créer une entrée dans la base de données
    * par rapport à un objet
    */
    public abstract T create(T obj);



    /**
    * Permet de mettre à jour les données d'une entrée dans la base
    * @param obj
    */
    public abstract T update(T obj);



    /**
    * Permet la suppression d'une entrée de la base
    * @param obj
    */
    //pour l'instant on impose pas dans le DAO , voir delete Ano
    //public abstract void delete(String s);


    /**
    * Permet la suppression de toutes les entrées du m^me nom de la base
    * @param obj
    */
    //*public abstract void deleteAll(T obj);

}
