package dao;
import java.util.List;
import metier.entities.Model;


public interface IModelDao {
public Model save(Model model);
public Model getModel(Long id);
public Model updateModel(Model model);
public void deleteModel(Long id);
public List<Model> getAllModels();
}
