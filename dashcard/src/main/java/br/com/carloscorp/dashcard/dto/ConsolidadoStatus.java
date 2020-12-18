package br.com.carloscorp.dashcard.dto;

public class ConsolidadoStatus {
	private String nomeAgente;
	private float volume;
	private int status;
	private long numeroOP; //é obrigatório ser long quando temos contagem de registros
	
	public ConsolidadoStatus(String nomeAgente, float volume, int status, long numeroOP) {
		super();
		this.nomeAgente = nomeAgente;
		this.volume = volume;
		this.status = status;
		this.numeroOP = numeroOP;
	}
	
	public String getNomeAgente() {
		return nomeAgente;
	}
	public void setNomeAgente(String nomeAgente) {
		this.nomeAgente = nomeAgente;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getNumeroOP() {
		return numeroOP;
	}
	public void setNumeroOP(long numeroOP) {
		this.numeroOP = numeroOP;
	}

	
	
}
