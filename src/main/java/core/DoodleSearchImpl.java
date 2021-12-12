package core;

import models.Doodle;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DoodleSearchImpl implements DoodleSearch {

    private Map<String, Doodle> doodleById;
    private Map<String, Doodle> doodleByTitle;




    public DoodleSearchImpl() {
        this.doodleById = new LinkedHashMap<>();
        this.doodleByTitle = new LinkedHashMap<>();
    }

    @Override
    public void addDoodle(Doodle doodle) {
        this.doodleById.put(doodle.getId(), doodle);
        this.doodleByTitle.put(doodle.getTitle(), doodle);
    }

    @Override
    public void removeDoodle(String doodleId) {
        if (!this.doodleById.containsKey(doodleId)) {
            throw new IllegalArgumentException();
        }

        Doodle toRemove = this.doodleById.remove(doodleId);
        this.doodleByTitle.remove(toRemove.getTitle());
    }

    @Override
    public int size() {
        return this.doodleById.size();
    }

    @Override
    public boolean contains(Doodle doodle) {
        return this.doodleById.containsKey(doodle.getId());
    }

    @Override
    public Doodle getDoodle(String id) {
        if(!doodleById.containsKey(id)){
            throw new IllegalArgumentException();
        }
        return this.doodleById.get(id);
    }

    @Override
    public double getTotalRevenueFromDoodleAds() {

       return this.doodleById.values().stream().filter(doodle -> doodle.getIsAd()).mapToDouble(i -> i.getRevenue() * i.getVisits()).sum();

    }

    @Override
    public void visitDoodle(String title) {
        if (!this.doodleByTitle.containsKey(title)) {
            throw new IllegalArgumentException();
        }
        Doodle doodle = this.doodleByTitle.get(title);
        doodle.setVisits(doodle.getVisits() + 1);

    }

    @Override
    public Iterable<Doodle> searchDoodles(String searchQuery) {

       return this.doodleById.values().stream().filter(doodle -> doodle.getTitle().contains(searchQuery)).sorted((first, second) -> {
           int result = Boolean.compare(!first.getIsAd(), !second.getIsAd());
           if (result == 0) {
               result = Integer.compare(first.getTitle().indexOf( searchQuery), second.getTitle().indexOf(searchQuery));
           }


           if (result == 0) {
               result = Double.compare(second.getVisits(), first.getVisits());
           }

           return result;
       })
               .collect(Collectors.toList());

    }

    @Override
    public Iterable<Doodle> getDoodleAds() {
        return this.doodleById.values().stream().filter(Doodle::getIsAd).sorted((first, second) -> {
            int result = Double.compare(second.getRevenue(), first.getRevenue());

            if (result == 0) {
                result = Integer.compare(second.getVisits(), first.getVisits());
            }

            return result;
        })
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Doodle> getTop3DoodlesByRevenueThenByVisits() {
        return this.doodleById.values().stream().filter(Doodle::getIsAd).sorted((first, second) -> {
            int result = Double.compare(second.getRevenue(), first.getRevenue());

            if (result == 0) {
                result = Integer.compare(second.getVisits(), first.getVisits());
            }

            return result;
        })
                .limit(3).collect(Collectors.toList());
    }
}
