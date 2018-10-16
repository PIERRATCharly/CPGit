import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chart.Bar;

public class App {
	
	public static void main(String[] args) {
		List<Etudiant> listeEtudiants = new ArrayList<>();
		
		Etudiant charly = new Etudiant("PIERRAT", "Charly");
		
		List<String> listeMatiere = new ArrayList<>();
		listeMatiere.add("Mathématiques");
		listeMatiere.add("Informatique");
		listeMatiere.add("Anglais");
		listeMatiere.add("Comptabilite");
		listeMatiere.add("Allemand");
		listeMatiere.add("Recherche Opérationelle");
		listeMatiere.add("Droit");
		listeMatiere.add("Algorithmique");
		listeMatiere.add("Structure des Ordinateurs");
		charly.ajouterMatiere(listeMatiere);
		
		charly.ajouterNote("Informatique", 15.);		
		listeEtudiants.add(charly);
		listeEtudiants.forEach(etudiants -> createFrame(etudiants));
	}
	
	/**
	 * Créer une frame pour un étudiant
	 * @param etudiant Etudiant
	 */
	private static void createFrame(Etudiant etudiant) {
		JFrame app = new JFrame(etudiant.getPrenom()+" "+etudiant.getNom());

		app.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Bar graphMoyenne = new Bar("Moyennes", "Matières", "Valeurs", etudiant.getMoyennes());
		graphMoyenne.personnaliserCouleurFond(Color.WHITE, Color.BLACK);
		app.getContentPane().add(graphMoyenne.getPanel(), BorderLayout.CENTER);
		
		JPanel panneauMatiere = new JPanel();
		app.getContentPane().add(panneauMatiere, BorderLayout.WEST);
		panneauMatiere.setLayout(new BorderLayout(0, 0));
		
		JPanel choixMatiere = new JPanel();
		panneauMatiere.add(choixMatiere, BorderLayout.NORTH);
		choixMatiere.setLayout(new GridLayout((etudiant.getMoyennes().keySet().toArray().length)/2, 0, 0, 0));
		
		JPanel graphiqueMatiere = new JPanel();
		panneauMatiere.add(graphiqueMatiere, BorderLayout.CENTER);
		CardLayout choixGraphique = new CardLayout();
		graphiqueMatiere.setLayout(choixGraphique);
		
		for(int i = 0; i < etudiant.getNotes().size(); i++) {
			String matiereI = (String)(etudiant.getNotes().keySet().toArray()[i]);
			Map<String, Double> mapNote = new HashMap<>();
			for(int j = 0; j<etudiant.getNotes().get(matiereI).size(); j++){
				mapNote.put("Note "+(j+1), etudiant.getNotes().get(matiereI).get(j));
			}
			Bar graphNote = new Bar("Notes "+matiereI, " ", "Valeurs", mapNote);
			graphNote.personnaliserCouleurFond(Color.WHITE, Color.BLACK);
			graphiqueMatiere.add(graphNote.getPanel(), matiereI);
			JButton bouton = new JButton(matiereI);
			bouton.setBackground(Color.WHITE);
			bouton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					choixGraphique.show(graphiqueMatiere, matiereI);
					
				}
			});
			choixMatiere.add(bouton);
		}
		
		app.setExtendedState(JFrame.MAXIMIZED_BOTH);
		app.setVisible(true);
	}
}