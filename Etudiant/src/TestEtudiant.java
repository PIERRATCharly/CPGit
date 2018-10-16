import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestEtudiant {
	
	private static Etudiant e;
	private static Map<String , List<Double>> mapNotes;
	private static Map<String , Double> mapMoyennes;
	
	@Before
	public static void init() {	
		e = new Etudiant("Testnom", "Testprenom");
		
		mapNotes = new HashMap<>();
		mapNotes.put("Matiere", new ArrayList<>());
		mapNotes.put("Generale", new ArrayList<>());
		
		mapMoyennes = new HashMap<>();
		mapMoyennes.put("Matiere", 0.);
		mapMoyennes.put("Generale", 0.);
	}
	
	@Test
	void testAjouterMatiereString() {
		init();
		e.ajouterMatiere("Matiere");
		
		assertEquals(mapNotes.get("Matiere"), e.getNotes().get("Matiere"));
		assertEquals(mapMoyennes.get("Matiere"), e.getMoyennes().get("Matiere"));
	}

	@Test
	void testAjouterMatiereListOfString() {
		init();
		List<String> matieres = new ArrayList<>();
		matieres.add("Matiere");
		matieres.add("Generale");
		e.ajouterMatiere(matieres);
		
		assertEquals(mapNotes.get("Matiere"), e.getNotes().get("Matiere"));
		assertEquals(mapMoyennes.get("Matiere"), e.getMoyennes().get("Matiere"));
		assertEquals(mapNotes.get("Generale"), e.getNotes().get("Generale"));
		assertEquals(mapMoyennes.get("Generale"), e.getMoyennes().get("Generale"));
	}

	@Test
	void testSupprimerMatiereString() {
		init();
		e.supprimerMatiere("Matiere");
		
		assertNull(e.getNotes().get("Matiere"));
		assertNull(e.getMoyennes().get("Matiere"));
	}

	@Test
	void testSupprimerMatiereListOfString() {
		init();
		List<String> matieres = new ArrayList<>();
		matieres.add("Matiere");
		matieres.add("Generale");
		e.ajouterMatiere(matieres);
		
		e.supprimerMatiere(matieres);
		
		assertNull(e.getNotes().get("Matiere"));
		assertNull(e.getMoyennes().get("Matiere"));
		assertNull(e.getNotes().get("Generale"));
		assertNull(e.getMoyennes().get("Generale"));
	}

	@Test
	void testAjouterNoteStringDoubleInt() {
		init();
		List<Double> list = new ArrayList<>();
		list.add(15.);
		list.add(15.);
		list.add(15.);
		e.ajouterMatiere("Matiere");
		
		e.ajouterNote("Matiere", 15., 3);
		
		assertEquals(list, e.getNotes().get("Matiere"));
		assertEquals(list, e.getNotes().get("Générale"));
		assertEquals((Object)15., e.getMoyennes().get("Matiere"));
		assertEquals((Object)15., e.getMoyennes().get("Générale"));
	}

	@Test
	void testAjouterNoteStringDouble() {
		init();
		List<Double> list = new ArrayList<>();
		list.add(15.);
		e.ajouterMatiere("Matiere");
		
		e.ajouterNote("Matiere", 15.);
		
		assertEquals(list, e.getNotes().get("Matiere"));
		assertEquals(list, e.getNotes().get("Générale"));
		assertEquals((Object)15., e.getMoyennes().get("Matiere"));
		assertEquals((Object)15., e.getMoyennes().get("Générale"));
		
		list.add(17.);
		e.ajouterNote("Matiere", 17.);
		
		assertEquals(list, e.getNotes().get("Matiere"));
		assertEquals(list, e.getNotes().get("Générale"));
		assertEquals((Object)16., e.getMoyennes().get("Matiere"));
		assertEquals((Object)16., e.getMoyennes().get("Générale"));
		
	}

	@Test
	void testAjouterNoteStringListOfDouble() {
		init();
		List<Double> list = new ArrayList<>();
		list.add(15.);
		list.add(17.);
		e.ajouterMatiere("Matiere");
		
		e.ajouterNote("Matiere", list);
		
		assertEquals(list, e.getNotes().get("Matiere"));
		assertEquals(list, e.getNotes().get("Générale"));
		assertEquals((Object)16., e.getMoyennes().get("Matiere"));
		assertEquals((Object)16., e.getMoyennes().get("Générale"));
		
	}
}
