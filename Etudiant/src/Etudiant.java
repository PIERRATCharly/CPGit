import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etudiant{

	// Nom de l'�tudiant
	private String nom;
	// Prenom de l'�tudiant
	private String prenom;
	// Map contenant une liste de notes pour chaque mati�res
	private Map<String, List<Double>> notes = new HashMap<>();
	// Map contenant les moyennes de chaque mati�res
	private Map<String, Double> moyennes = new HashMap<>();
	
	/**
	 * Constructeur d'Etudiant avec nom et prenom
	 * @param nom nom de l'�tudiant
	 * @param prenom prenom de l'�tudiant
	 */
	public Etudiant(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		ajouterMatiere("G�n�rale");
	}
	
	/**
	 * Constructeur d'Etudiant avec nom et prenom et liste de mati�re
	 * @param nom nom de l'�tudiant
	 * @param prenom prenom de l'�tudiant
	 * @param matieres mati�res de l'�tudiant
	 */
	public Etudiant(String nom, String prenom, List<String> matieres) {
		this(nom, prenom);
		ajouterMatiere(matieres);
	}
	
	/**
	 * Constructeur d'Etudiant par d�faut
	 */
	public Etudiant() {
		
	}
	
	/**
	 * Ajoute une mati�re
	 * @param matiere mati�re � ajouter
	 */
	public void ajouterMatiere(String matiere) {
		if (!(notes.containsKey(matiere))) {
			notes.put(matiere, new ArrayList<>());
			moyennes.put(matiere, 0.);
		}
	}
	
	/**
	 * Ajoute une liste de mati�res
	 * @param matieres mati�res � ajouter
	 */
	public void ajouterMatiere(List<String> matieres) {
		matieres.forEach(matiere -> ajouterMatiere(matiere));
	}
	
	/**
	 * Supprime une mati�re
	 * @param matiere mati�re � supprimer
	 */
	public void supprimerMatiere(String matiere) {
		notes.remove(matiere);
		moyennes.remove(matiere);
	}
	
	/**
	 * Supprime une liste de mati�re
	 * @param matieres mati�res � supprimer
	 */
	public void supprimerMatiere(List<String> matieres) {
		matieres.forEach(matiere -> supprimerMatiere(matiere));
	}
	
	/**
	 * Ajoute une note dans une mati�re avec un coefficient
	 * @param matiere matiere de la note
	 * @param note valeur de la note
	 * @param coefficient coefficient de la note
	 */
	public void ajouterNote(String matiere, Double note, int coefficient) {
		List<Double> listeNotes = notes.get(matiere);
		afficherNotes();
		System.out.println(matiere+note+coefficient+listeNotes);
		List<Double> listeGenerale = notes.get("G�n�rale");
		for(int i = 0; i < coefficient; i++) {
			listeNotes.add(note);
			listeGenerale.add(note);
		}
		notes.put(matiere, listeNotes);
		notes.put("G�n�rale", listeGenerale);
		calculerMoyennes();
	}

	/**
	 * Ajoute une note dans une mati�re
	 * @param matiere mati�re de la note
	 * @param note valeur de la note
	 */
	public void ajouterNote(String matiere, Double note) {
		ajouterNote(matiere, note, 1);
	}
	
	/**
	 * Ajoute une liste de notes dans une mati�re
	 * @param matiere mati�re des notes
	 * @param notes liste de notes
	 */
	public void ajouterNote(String matiere, List<Double> notes) {
		notes.forEach(note -> ajouterNote(matiere, note, 1));
	}
	
	/**
	 * Calcule la moyenne d'une liste de notes
	 * @param notes liste de notes
	 * @return valeur de la moyenne
	 */
	private Double calculerMoyenne(List<Double> notes) {
		Double moyenne = 0.;
		for(int i = 0; i < notes.size(); i++) {
			moyenne = moyenne + notes.get(i);
		}
		return moyenne/notes.size();
	}

	/**
	 * Calcule les moyennes de chaque mati�re
	 */
	public void calculerMoyennes() {
		notes.forEach((matiere, note) -> moyennes.put(matiere, calculerMoyenne(note)));
	}

	/**
	 * Affiche les notes de toutes les mati�res
	 */
	public void afficherNotes() {
		notes.forEach((matiere, note) -> {
			System.out.println("Notes " + matiere);
			note.forEach(n -> System.out.print(n+" "));
			System.out.println("");
		});		
	}
	
	/**
	 * Affiche les moyennes de toutes les mati�res
	 */
	public void afficherMoyennes() {
		moyennes.forEach((matiere, moyenne) -> System.out.println("Moyenne " + matiere + ": " + moyenne));
	}
	
	public Map<String, List<Double>> getNotes() {
		return notes;
	}

	public Map<String, Double> getMoyennes() {
		return moyennes;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}