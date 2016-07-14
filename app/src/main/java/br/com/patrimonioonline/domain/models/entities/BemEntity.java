package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by helio on 15/06/16.
 */

public class BemEntity extends RealmObject {

    @PrimaryKey
    private int id;
    private String descricao;
    private BemTipoEntity bemTipoEntity;
    private BemTipoDepreciacaoEntity bemTipoDepreciacaoEntity;
    private ClassificacaoEntity classificacaoEntity;
    private AquisicaoEntity aquisicaoEntity;
    private DepartamentoEntity departamentoEntity;
    private ConvenioEntity convenioEntity;
    private SituacaoEntity situacaoEntity;
    private String numeroPlaca;
    private Double valorAquisicao;
    private Double valorResidual;
    private String dataAquisicao;
    private String latitude;
    private String longitude;
    private RealmList<BemImagensEntity> listaBemImageEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BemTipoEntity getBemTipoEntity() {
        return bemTipoEntity;
    }

    public void setBemTipoEntity(BemTipoEntity bemTipoEntity) {
        this.bemTipoEntity = bemTipoEntity;
    }

    public BemTipoDepreciacaoEntity getBemTipoDepreciacaoEntity() {
        return bemTipoDepreciacaoEntity;
    }

    public void setBemTipoDepreciacaoEntity(BemTipoDepreciacaoEntity bemTipoDepreciacaoEntity) {
        this.bemTipoDepreciacaoEntity = bemTipoDepreciacaoEntity;
    }

    public ClassificacaoEntity getClassificacaoEntity() {
        return classificacaoEntity;
    }

    public void setClassificacaoEntity(ClassificacaoEntity classificacaoEntity) {
        this.classificacaoEntity = classificacaoEntity;
    }

    public AquisicaoEntity getAquisicaoEntity() {
        return aquisicaoEntity;
    }

    public void setAquisicaoEntity(AquisicaoEntity aquisicaoEntity) {
        this.aquisicaoEntity = aquisicaoEntity;
    }

    public DepartamentoEntity getDepartamentoEntity() {
        return departamentoEntity;
    }

    public void setDepartamentoEntity(DepartamentoEntity departamentoEntity) {
        this.departamentoEntity = departamentoEntity;
    }

    public ConvenioEntity getConvenioEntity() {
        return convenioEntity;
    }

    public void setConvenioEntity(ConvenioEntity convenioEntity) {
        this.convenioEntity = convenioEntity;
    }

    public SituacaoEntity getSituacaoEntity() {
        return situacaoEntity;
    }

    public void setSituacaoEntity(SituacaoEntity situacaoEntity) {
        this.situacaoEntity = situacaoEntity;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public Double getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(Double valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public Double getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(Double valorResidual) {
        this.valorResidual = valorResidual;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public RealmList<BemImagensEntity> getListaBemImageEntities() {
        return listaBemImageEntities;
    }

    public void setListaBemImageEntities(RealmList<BemImagensEntity> listaBemImageEntities) {
        this.listaBemImageEntities = listaBemImageEntities;
    }

    /*public String converterParaJson(){

        Gson gson = null;

        gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(this.getClass(), new BemEntity.BemEntitySerializer())
                .registerTypeAdapter(BemTipoEntity.class, new BemTipoEntity.BemTipoSerializer())
                .registerTypeAdapter(BemTipoDepreciacaoEntity.class, new BemTipoDepreciacaoEntity.BemTipoDepreciacaoSerializer())
                .registerTypeAdapter(ClassificacaoEntity.class, new ClassificacaoEntity.ClassificacaoSerializer())
                .registerTypeAdapter(AquisicaoEntity.class, new AquisicaoEntity.AquisicaoSerializer())
                .registerTypeAdapter(DepartamentoEntity.class, new DepartamentoEntity.DepartamentoSerializer())
                .registerTypeAdapter(ConvenioEntity.class, new ConvenioEntity.ConvenioSerializer())
                //.registerTypeAdapter(SituacaoEntity.class, new SituacaoEntity.SituacaoSerializer())

                .create();

        String json = gson.toJson(this);

        return json;
    }

    static class BemEntitySerializer implements JsonSerializer<BemEntity> {

        @Override
        public JsonElement serialize(BemEntity src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.id);
            jsonObject.addProperty("descricao", src.descricao);
            jsonObject.add("bemTipoEntity", context.serialize(src.bemTipoEntity));
            jsonObject.add("bemTipoDepreciacaoEntity", context.serialize(src.bemTipoDepreciacaoEntity));
            jsonObject.add("classificacaoEntity", context.serialize(src.classificacaoEntity));
            jsonObject.add("aquisicaoEntity", context.serialize(src.aquisicaoEntity));
            jsonObject.add("departamentoEntity", context.serialize(src.departamentoEntity));
            jsonObject.add("convenioEntity", context.serialize(src.convenioEntity));
            //jsonObject.add("situacaoEntity", context.serialize(src.situacaoEntity));
            jsonObject.addProperty("numeroPlaca", src.numeroPlaca);
            jsonObject.addProperty("valorAquisicao", src.valorAquisicao);
            jsonObject.addProperty("valorResidual", src.valorResidual);
            jsonObject.addProperty("dataAquisicao", src.dataAquisicao);

            return jsonObject;
        }
    }*/

}
