package br.com.patrimonioonline.domain.ui;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

import br.com.patrimonioonline.domain.ui.camera.GraphicOverlay;

/**
 * Created by helio on 14/07/16.
 */

class BemBarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private GraphicOverlay<BemBarcodeGraphic> mGraphicOverlay;

    BemBarcodeTrackerFactory(GraphicOverlay<BemBarcodeGraphic> barcodeGraphicOverlay) {
        mGraphicOverlay = barcodeGraphicOverlay;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        BemBarcodeGraphic graphic = new BemBarcodeGraphic(mGraphicOverlay);
        return new BemBarcodeGraphicTracker(mGraphicOverlay, graphic);
    }

}
