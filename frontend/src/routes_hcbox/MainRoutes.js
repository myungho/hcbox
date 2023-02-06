import {lazy} from 'react';

// project import
import Loadable from 'components/Loadable';
import MainLayout from 'layout/MainLayout';

// render - dashboard
const DashboardDefault = Loadable(lazy(() => import('pages_hcbox/dashboard')));

// render - sample page
const SamplePage = Loadable(lazy(() => import('pages_hcbox/extra-pages/SamplePage')));
const ProductPage = Loadable(lazy(() => import('pages_hcbox/elite/ProductPage')));
const SchoolPage = Loadable(lazy(() => import('pages_hcbox/elite/SchoolPage')));

// render - utilities
// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
    path: '/',
    element: <MainLayout />,
    children: [
        {
            path: '/',
            element: <DashboardDefault />
        },
        {
            path: 'dashboard',
            children: [
                {
                    path: 'default',
                    element: <DashboardDefault />
                }
            ]
        },
        {
            path: 'products',
            element: <ProductPage />
        },
        {
            path: 'schools',
            element: <SchoolPage />
        },
        {
            path: 'sample',
            element: <SamplePage />
        }
    ]
};

export default MainRoutes;
