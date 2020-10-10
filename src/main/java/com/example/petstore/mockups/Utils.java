package com.example.petstore.mockups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.petstore.backend.api.model.Pet.StatusEnum;
import com.example.petstore.backend.db.CategoryBE;
import com.example.petstore.backend.db.PetBE;
import com.example.petstore.backend.db.TagBE;

public class Utils {

	public static List<PetBE> makePets() {
		List<PetBE> pets = new ArrayList<PetBE>();

		CategoryBE cat = new CategoryBE();
		cat.setId(0);
		cat.setName("c1");

		StatusEnum se = StatusEnum.AVAILABLE;
		List<String> urls = Arrays.asList();

		TagBE[] t = t();

		pets.add(new PetBE(0, "a", cat, urls, b(t, 1,2), se));
		pets.add(new PetBE(1, "b", cat, urls, b(t, 2,3), se));
		pets.add(new PetBE(2, "c", cat, urls, b(t, 3), se));
		pets.add(new PetBE(3, "d", cat, urls, b(t, 3,4), se));
		pets.add(new PetBE(4, "e", cat, urls, b(t, 2,4), se));
		pets.add(new PetBE(5, "f", cat, urls, b(t, 1,4), se));
		return pets;
	}

	private static TagBE[] t() {

		String[] names = {"a","b","c","d","e"};		
		TagBE[] ts = new TagBE[names.length];

		for (int i = 0; i < names.length; i++) {
			TagBE t = new TagBE();
			t.setId(i);
			t.setName(names[i]);

			ts[i] = t;
		}
		return ts; 
	}

	private static List<TagBE> b(TagBE[] allTags, Integer... is) {
		TagBE[] tags = new TagBE[is.length];
		for (int i = 0; i < is.length; i++) {
			tags[i] = allTags[is[i]];			
		}
		return Arrays.asList(tags);
	}

}
