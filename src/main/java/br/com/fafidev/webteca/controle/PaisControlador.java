package br.com.fafidev.webteca.controle;

import br.com.fafidev.webteca.controle.util.JsfUtil;
import br.com.fafidev.webteca.entidade.Instituicao;
import br.com.fafidev.webteca.entidade.Pais;
import br.com.fafidev.webteca.negocio.PaisDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author fernando
 */
@ManagedBean
@SessionScoped
public class PaisControlador implements Serializable {

    private Pais pais;
    private PaisDAO paisDAO;
    private boolean edicao;

    public PaisControlador() {
        newInstances();
    }

    public String salvar() {
        try {
            if (edicao) {
                paisDAO.update(pais);
            } else {
                paisDAO.save(pais);
            }
            JsfUtil.addSuccessMessage("Instituição salva com sucesso!");
            return prepararLista();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro ao salvar instituição");
            return null;
        }
    }

    public String excluir(Pais pais) {
        try {
            paisDAO.delete(pais);
            JsfUtil.addSuccessMessage("Instituição removida com sucesso!");
            return prepararLista();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro ao excluir instituição");
            return null;
        }
    }

    public String prepararLista() {
        newInstances();
        return "lista";
    }

    public String prepararEdicao(Pais pais) {
        newInstances();
        this.pais = pais;
        edicao = true;
        return "edita";
    }

    public String prepararNovo() {
        newInstances();
        edicao = true;
        return "edita";
    }

    public String prepararVer(Pais pais) {
        newInstances();
        this.pais = pais;
        edicao = false;
        return "edita";
    }

    private void newInstances() {
        this.pais = new Pais();
        this.paisDAO = new PaisDAO();
    }

    public List<Pais> getPaises() {
        return paisDAO.listAll();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public PaisDAO getPaisDAO() {
        return paisDAO;
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

}
